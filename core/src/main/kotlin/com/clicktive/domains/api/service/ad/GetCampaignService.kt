package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.dto.ad.CampaignResponse
import com.clicktive.domains.api.data.dto.ad.DashboardValue
import com.clicktive.domains.api.data.entity.ad.RawCampaign
import com.clicktive.domains.api.repository.ad.RawCampaignRepository
import com.clicktive.framework.exception.ServiceException
import com.clicktive.framework.util.DateUtils.Companion.getPreviousMonth
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.stereotype.Service

@Service
class GetCampaignService(
    private val rawCampaignRepository: RawCampaignRepository,
    private val getCampaignMonthlyService: GetCampaignMonthlyService,
    private val getCampaignDailyService: GetCampaignDailyService
) {
    fun getRawCampaign(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<RawCampaign> {
        return rawCampaignRepository.findAllByBrandNoAndCountryNoAndMonth(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month
        )
    }

    fun getCampaignResponse(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): CampaignResponse {
        val previousMonth = getPreviousMonth(month)

        val campaignPreviousMonthly =
            getCampaignMonthlyService.getCampaignMonthly(
                brandNo = brandNo,
                countryNo = countryNo,
                month = previousMonth
            )
        val campaignCurrentMonthly =
            getCampaignMonthlyService.getCampaignMonthly(brandNo = brandNo, countryNo = countryNo, month = month)
                ?: throw ServiceException("")
        val campaignDaily =
            getCampaignDailyService.getCampaignDaily(brandNo = brandNo, countryNo = countryNo, month = month)
                .sortedBy { it.camaignDate }

        var adExpenseAmt: MutableMap<String, Float> = mutableMapOf()
        var adSalesAmt: MutableMap<String, Float> = mutableMapOf()
        var organicSalesAmt: MutableMap<String, Float> = mutableMapOf()
        var totalRoas: MutableMap<String, Float> = mutableMapOf()
        var roas: MutableMap<String, Float> = mutableMapOf()
        var acos: MutableMap<String, Float> = mutableMapOf()
        var viewNum: MutableMap<String, Int> = mutableMapOf()
        var clickNum: MutableMap<String, Int> = mutableMapOf()
        var pv: MutableMap<String, Int> = mutableMapOf()
        var ctr: MutableMap<String, Float> = mutableMapOf()
        var cpc: MutableMap<String, Float> = mutableMapOf()
        var cpm: MutableMap<String, Float> = mutableMapOf()

        campaignDaily.forEach {
            val camaignDate = it.camaignDate ?: ""

            adExpenseAmt[camaignDate] = it.adExpenseAmt
            adSalesAmt[camaignDate] = it.adSalesAmt
            organicSalesAmt[camaignDate] = it.organicSalesAmt
            totalRoas[camaignDate] = it.totalRoas
            roas[camaignDate] = it.roas
            acos[camaignDate] = it.acos
            viewNum[camaignDate] = it.viewNum
            clickNum[camaignDate] = it.clickNum
            pv[camaignDate] = it.pv
            ctr[camaignDate] = it.ctr
            cpc[camaignDate] = it.cpc
            cpm[camaignDate] = it.cpm
        }

        return CampaignResponse(
            adExpenseAmt = DashboardValue(
                previousMonthValue = campaignPreviousMonthly?.adExpenseAmt,
                currentMonthValue = campaignCurrentMonthly.adExpenseAmt,
                currentMonthValues = adExpenseAmt
            ),
            adSalesAmt = DashboardValue(
                previousMonthValue = campaignPreviousMonthly?.adSalesAmt,
                currentMonthValue = campaignCurrentMonthly.adSalesAmt,
                currentMonthValues = adSalesAmt
            ),
            organicSalesAmt = DashboardValue(
                previousMonthValue = campaignPreviousMonthly?.organicSalesAmt,
                currentMonthValue = campaignCurrentMonthly.organicSalesAmt,
                currentMonthValues = organicSalesAmt
            ),
            totalRoas = DashboardValue(
                previousMonthValue = campaignPreviousMonthly?.totalRoas,
                currentMonthValue = campaignCurrentMonthly.totalRoas,
                currentMonthValues = totalRoas
            ),
            roas = DashboardValue(
                previousMonthValue = campaignPreviousMonthly?.roas,
                currentMonthValue = campaignCurrentMonthly.roas,
                currentMonthValues = roas
            ),
            acos = DashboardValue(
                previousMonthValue = campaignPreviousMonthly?.acos,
                currentMonthValue = campaignCurrentMonthly.acos,
                currentMonthValues = acos
            ),
            viewNum = DashboardValue(
                previousMonthValue = campaignPreviousMonthly?.viewNum,
                currentMonthValue = campaignCurrentMonthly.viewNum,
                currentMonthValues = viewNum
            ),
            clickNum = DashboardValue(
                previousMonthValue = campaignPreviousMonthly?.clickNum,
                currentMonthValue = campaignCurrentMonthly.clickNum,
                currentMonthValues = clickNum
            ),
            pv = DashboardValue(
                previousMonthValue = campaignPreviousMonthly?.pv,
                currentMonthValue = campaignCurrentMonthly.pv,
                currentMonthValues = pv
            ),
            ctr = DashboardValue(
                previousMonthValue = campaignPreviousMonthly?.ctr,
                currentMonthValue = campaignCurrentMonthly.ctr,
                currentMonthValues = ctr
            ),
            cpc = DashboardValue(
                previousMonthValue = campaignPreviousMonthly?.cpc,
                currentMonthValue = campaignCurrentMonthly.cpc,
                currentMonthValues = cpc
            ),
            cpm = DashboardValue(
                previousMonthValue = campaignPreviousMonthly?.cpm,
                currentMonthValue = campaignCurrentMonthly.cpm,
                currentMonthValues = cpm
            )
        )
    }
}