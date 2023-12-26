package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.code.campaignTypeCd
import com.clicktive.domains.api.data.dto.ad.ResultFileRequest
import com.clicktive.domains.api.data.entity.ad.*
import com.clicktive.framework.util.Mapper
import org.springframework.stereotype.Service
import java.lang.Integer.min
import java.time.LocalDate
import java.time.YearMonth

@Service
class AdResultService(
    private val getSalesService: GetSalesService,
    private val getOrderService: GetOrderService,
    private val getCampaignService: GetCampaignService,
    private val salesMonthlyService: SalesMonthlyService,
    private val salesDailyService: SalesDailyService,
    private val campaignMonthlyService: CampaignMonthlyService,
    private val campaignDailyService: CampaignDailyService,
    private val campaignTypeService: CampaignTypeService
) {
    fun save(resultFileRequest: ResultFileRequest) {
        val brandNo = resultFileRequest.brandNo
        val countryNo = resultFileRequest.countryNo
        val month = resultFileRequest.month

        val yearMonth = YearMonth.of(resultFileRequest.month.substring(0, 4).toInt(), resultFileRequest.month.substring(5).toInt())
        val firstDayOfMonth = yearMonth.atDay(1)
        val lastDayOfMonth = yearMonth.atEndOfMonth()

        val salesDaily = getSalesDaily(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            firstDayOfMonth = firstDayOfMonth,
            lastDayOfMonth = lastDayOfMonth
        )

        val salesMonthly = getSalesMonthly(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            salesDaily = salesDaily
        )

        val rawCampaign = getCampaignService.getRawCampaign(brandNo = brandNo, countryNo = countryNo, month = month)

        val campaignDaily = getCampaignDaily(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            firstDayOfMonth = firstDayOfMonth,
            lastDayOfMonth = lastDayOfMonth,
            rawCampaign = rawCampaign,
            salesDaily = salesDaily
        )

        val campaignMonthly = getCampaignMonthly(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            campaignDaily = campaignDaily
        )

        val campaignType = getCampaignType(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            rawCampaign = rawCampaign
        )

        salesDailyService.createSalesDaily(Mapper.convertAll(salesDaily))
        salesMonthlyService.createSalesMonthly(Mapper.convert(salesMonthly))
        campaignDailyService.createCampaignDaily(Mapper.convertAll(campaignDaily))
        campaignMonthlyService.createCampaignMonthly(Mapper.convert(campaignMonthly))
        campaignTypeService.createCampaignType(Mapper.convert(campaignType))
    }

    private fun getSalesDaily(
        brandNo: Long,
        countryNo: Long,
        month: String,
        firstDayOfMonth: LocalDate,
        lastDayOfMonth: LocalDate
    ): List<SalesDaily> {
        val rawSales = getSalesService.getRawSales(brandNo = brandNo, countryNo = countryNo, month = month)
        val rawOrder = getOrderService.getRawOrder(brandNo = brandNo, countryNo = countryNo, month = month)

        return firstDayOfMonth.datesUntil(
            lastDayOfMonth.plusDays(1)
        ).map { localDate ->
            val sales = rawSales.filter { it.purchaseDate == localDate.toString() }
            val order = rawOrder.filter { it.purchaseDate?.toLocalDate() == localDate }

            // TODO 매출(salesAmt)이랑 주문한 상품 판매량(salesQty)이랑 계산값 같음?
            val salesAmt = sales.sumOf { it.salesAmt }.toFloat()
            val orderQty = order.filter {
                it.amazonOrerId?.startsWith("S", ignoreCase = true) != true
                        || it.orderStatus == "Cancelled"
            }.groupBy { it.amazonOrerId }.size
            val customerUnitAmt = (orderQty.takeIf { it > 0 }?.let { salesAmt.div(orderQty) } ?: 0).toFloat()
            val visitorNum = sales.sumOf { it.sessionTotalNum }
            val conversionRate =
                ((visitorNum.takeIf { it > 0 }?.let { orderQty.div(visitorNum) } ?: 0)).times(100).toFloat()
            val claimQty = sales.sumOf { it.claimQty }
            val salesOrderQty = order.sumOf { it.orderQty }.toFloat()
            val refundRate = (salesOrderQty.takeIf { it > 0 }?.let { claimQty.div(salesOrderQty) } ?: 0).toFloat()
            val totalOrderItemQty = sales.sumOf { it.orderItemQty }
            val totalOrderQty = sales.sumOf { it.orderQty }
            val salesQty = sales.sumOf { it.salesAmt }.toFloat()
            val orderItemAvgQty = sales.sumOf { it.orderItemAvgQty }
            val orderItemAvgAmt = sales.sumOf { it.orderItemAvgAmt }
            val avgSalesQtyRate =
                (orderItemAvgAmt.takeIf { it > 0 }?.let { orderItemAvgQty.div(orderItemAvgAmt) } ?: 0).toFloat()
            val avgSalesAmtRate =
                (orderItemAvgQty.takeIf { it > 0 }?.let { orderItemAvgAmt.div(orderItemAvgQty) } ?: 0).toFloat()
            val pv = sales.sumOf { it.pageViewTotalNum }

            SalesDaily(
                salesDailyNo = null,
                brandNo = brandNo,
                countryNo = countryNo,
                month = month,
                purchaseDate = localDate.toString(),
                salesAmt = salesAmt,
                orderQty = orderQty,
                customerUnitAmt = customerUnitAmt,
                visitorNum = visitorNum,
                conversionRate = conversionRate,
                refundRate = refundRate,
                totalOrderItemQty = totalOrderItemQty,
                totalOrderQty = totalOrderQty,
                salesQty = salesQty,
                avgSalesQtyRate = avgSalesQtyRate,
                avgSalesAmtRate = avgSalesAmtRate,
                pv = pv
            )
        }.toList()
    }

    private fun getSalesMonthly(
        brandNo: Long,
        countryNo: Long,
        month: String,
        salesDaily: List<SalesDaily>
    ): SalesMonthly {
        return SalesMonthly(
            salesMonthlyNo = null,
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            salesAmt = salesDaily.map { it.salesAmt }.sum(),
            orderQty = salesDaily.sumOf { it.orderQty },
            customerUnitAmt = salesDaily.map { it.customerUnitAmt }.sum(),
            visitorNum = salesDaily.sumOf { it.visitorNum },
            conversionRate = salesDaily.map { it.conversionRate }.sum(),
            refundRate = salesDaily.map { it.refundRate }.sum(),
            totalOrderItemQty = salesDaily.sumOf { it.totalOrderItemQty },
            totalOrderQty = salesDaily.sumOf { it.totalOrderQty },
            salesQty = salesDaily.map { it.salesQty }.sum(),
            avgSalesQtyRate = salesDaily.map { it.avgSalesQtyRate }.sum(),
            avgSalesAmtRate = salesDaily.map { it.avgSalesAmtRate }.sum()
        )
    }

    private fun getCampaignDaily(
        brandNo: Long,
        countryNo: Long,
        month: String,
        firstDayOfMonth: LocalDate,
        lastDayOfMonth: LocalDate,
        rawCampaign: List<RawCampaign>,
        salesDaily: List<SalesDaily>
    ): List<CampaignDaily> {
        return firstDayOfMonth.datesUntil(
            lastDayOfMonth.plusDays(1)
        ).map { localDate ->
            val campaign = rawCampaign.filter { it.campaignDate == localDate.toString() }
            val sales = salesDaily.single { it.purchaseDate == localDate.toString() }

            val adExpenseAmt = campaign.map { it.expenseAmt }.sum()
            val adSalesAmt = campaign.map { it.salesAmt }.sum()
            val organicSalesAmt = sales.orderQty.minus(adSalesAmt)
            val totalRoas =
                (adExpenseAmt.takeIf { it > 0 }?.let { sales.orderQty.div(adExpenseAmt).times(100) } ?: 0).toFloat()
            val roas =
                (adExpenseAmt.takeIf { it > 0 }?.let { organicSalesAmt.div(adExpenseAmt).times(100) } ?: 0).toFloat()
            val acos =
                (organicSalesAmt.takeIf { it > 0 }?.let { adExpenseAmt.div(organicSalesAmt).times(100) } ?: 0).toFloat()
            val viewNum = campaign.sumOf { it.viewNum }
            val clickNum = campaign.sumOf { it.clickNum }
            val pv = sales.pv
            val ctr = (viewNum.takeIf { it > 0 }?.let { clickNum.div(viewNum).times(100) } ?: 0).toFloat()
            val cpc = (clickNum.takeIf { it > 0 }?.let { adExpenseAmt.div(clickNum) } ?: 0).toFloat()
            val cpm = (viewNum.takeIf { it > 0 }?.let { adSalesAmt.div(viewNum).times(1000) } ?: 0).toFloat()

            CampaignDaily(
                campaignDailyNo = null,
                brandNo = brandNo,
                countryNo = countryNo,
                month = month,
                camaignDate = localDate.toString(),
                adExpenseAmt = adExpenseAmt,
                adSalesAmt = adSalesAmt,
                organicSalesAmt = organicSalesAmt,
                totalRoas = totalRoas,
                roas = roas,
                acos = acos,
                viewNum = viewNum,
                clickNum = clickNum,
                pv = pv,
                ctr = ctr,
                cpc = cpc,
                cpm = cpm
            )
        }.toList()
    }

    private fun getCampaignMonthly(
        brandNo: Long,
        countryNo: Long,
        month: String,
        campaignDaily: List<CampaignDaily>
    ): CampaignMonthly {
        return CampaignMonthly(
            campaignMonthlyNo = null,
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            adExpenseAmt = campaignDaily.map { it.adExpenseAmt }.sum(),
            adSalesAmt = campaignDaily.map { it.adSalesAmt }.sum(),
            organicSalesAmt = campaignDaily.map { it.organicSalesAmt }.sum(),
            totalRoas = campaignDaily.map { it.totalRoas }.sum(),
            roas = campaignDaily.map { it.roas }.sum(),
            acos = campaignDaily.map { it.acos }.sum(),
            viewNum = campaignDaily.sumOf { it.viewNum },
            clickNum = campaignDaily.sumOf { it.clickNum },
            pv = campaignDaily.sumOf { it.pv },
            ctr = campaignDaily.map { it.ctr }.sum(),
            cpc = campaignDaily.map { it.cpc }.sum(),
            cpm = campaignDaily.map { it.cpm }.sum()
        )
    }

    private fun getCampaignType(
        brandNo: Long,
        countryNo: Long,
        month: String,
        rawCampaign: List<RawCampaign>
    ): List<CampaignType> {
        return campaignTypeCd.map { (detailCode, detailCodeName) ->
            // TODO 일단 SD,SP,SB 포함 여부만 체크했는데 뒤에 숫자도 구분해야 하는지?
            val campaign = rawCampaign.filter {
                it.campaignType?.take(min(2, (it.campaignType?.length ?: 0)))
                    ?.equals(detailCodeName.take(2), ignoreCase = true) == true
            }

            val adExpenseAmt = campaign.map { it.expenseAmt }.sum()
            val adSalesAmt = campaign.map { it.salesAmt }.sum()
            // TODO CampaignDaily랑 계산 방법이 다른데 상관 없는지
            val roas =
                (adExpenseAmt.takeIf { it > 0 }?.let { adExpenseAmt.div(adExpenseAmt).times(100) } ?: 0).toFloat()
            val acos = (adSalesAmt.takeIf { it > 0 }?.let { adExpenseAmt.div(adSalesAmt).times(100) } ?: 0).toFloat()
            val viewNum = campaign.sumOf { it.viewNum }
            val clickNum = campaign.sumOf { it.clickNum }
            val ctr = (viewNum.takeIf { it > 0 }?.let { clickNum.div(viewNum).times(100) } ?: 0).toFloat()
            val cpc = (clickNum.takeIf { it > 0 }?.let { adExpenseAmt.div(clickNum) } ?: 0).toFloat()

            CampaignType(
                campaignTypeNo = null,
                brandNo = brandNo,
                countryNo = countryNo,
                month = month,
                campaignTypeCd = detailCode,
                adExpenseAmt = adExpenseAmt,
                adSalesAmt = adSalesAmt,
                roas = roas,
                acos = acos,
                ctr = ctr,
                cpc = cpc
            )
        }
    }
}