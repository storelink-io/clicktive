package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.dto.ad.DashboardValue
import com.clicktive.domains.api.data.dto.ad.SalesResponse
import com.clicktive.domains.api.data.entity.ad.RawSales
import com.clicktive.domains.api.repository.ad.RawSalesRepository
import com.clicktive.framework.exception.ServiceException
import com.clicktive.framework.util.DateUtils.Companion.getPreviousMonth
import org.springframework.stereotype.Service

@Service
class GetSalesService(
    private val rawSalesRepository: RawSalesRepository,
    private val getSalesMonthlyService: GetSalesMonthlyService,
    private val getSalesDailyService: GetSalesDailyService
) {
    fun getRawSales(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<RawSales> {
        return rawSalesRepository.findAllByBrandNoAndCountryNoAndMonth(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month
        )
    }

    fun getSalesResponse(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): SalesResponse {
        val previousMonth = getPreviousMonth(month)

        val salesPreviousMonthly =
            getSalesMonthlyService.getSalesMonthly(brandNo = brandNo, countryNo = countryNo, month = previousMonth)
        val salesCurrentMonthly =
            getSalesMonthlyService.getSalesMonthly(brandNo = brandNo, countryNo = countryNo, month = month)
                ?: throw ServiceException("")
        val salesDaily = getSalesDailyService.getSalesDaily(brandNo = brandNo, countryNo = countryNo, month = month)
            .sortedBy { it.purchaseDate }

        var salesAmt: MutableMap<String, Float> = mutableMapOf()
        var orderQty: MutableMap<String, Int> = mutableMapOf()
        var customerUnitAmt: MutableMap<String, Float> = mutableMapOf()
        var visitorNum: MutableMap<String, Int> = mutableMapOf()
        var conversionRate: MutableMap<String, Float> = mutableMapOf()
        var refundRate: MutableMap<String, Float> = mutableMapOf()

        salesDaily.forEach {
            val purchaseDate = it.purchaseDate ?: ""

            salesAmt[purchaseDate] = it.salesAmt
            orderQty[purchaseDate] = it.orderQty
            customerUnitAmt[purchaseDate] = it.customerUnitAmt
            visitorNum[purchaseDate] = it.visitorNum
            conversionRate[purchaseDate] = it.conversionRate
            refundRate[purchaseDate] = it.refundRate
        }

        return SalesResponse(
            salesAmt = DashboardValue(
                previousMonthValue = salesPreviousMonthly?.salesAmt,
                currentMonthValue = salesCurrentMonthly.salesAmt,
                currentMonthValues = salesAmt
            ),
            orderQty = DashboardValue(
                previousMonthValue = salesPreviousMonthly?.orderQty,
                currentMonthValue = salesCurrentMonthly.orderQty,
                currentMonthValues = orderQty
            ),
            customerUnitAmt = DashboardValue(
                previousMonthValue = salesPreviousMonthly?.customerUnitAmt,
                currentMonthValue = salesCurrentMonthly.customerUnitAmt,
                currentMonthValues = customerUnitAmt
            ),
            visitorNum = DashboardValue(
                previousMonthValue = salesPreviousMonthly?.visitorNum,
                currentMonthValue = salesCurrentMonthly.visitorNum,
                currentMonthValues = visitorNum
            ),
            conversionRate = DashboardValue(
                previousMonthValue = salesPreviousMonthly?.conversionRate,
                currentMonthValue = salesCurrentMonthly.conversionRate,
                currentMonthValues = conversionRate
            ),
            refundRate = DashboardValue(
                previousMonthValue = salesPreviousMonthly?.refundRate,
                currentMonthValue = salesCurrentMonthly.refundRate,
                currentMonthValues = refundRate
            )
        )
    }
}