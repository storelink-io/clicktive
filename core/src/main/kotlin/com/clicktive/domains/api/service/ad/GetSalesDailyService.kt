package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.dto.ad.SalesDailyResponse
import com.clicktive.domains.api.data.entity.ad.SalesDaily
import com.clicktive.domains.api.repository.ad.SalesDailyRepository
import org.springframework.stereotype.Service

@Service
class GetSalesDailyService(
    private val salesDailyRepository: SalesDailyRepository,
    private val getCampaignDailyService: GetCampaignDailyService
) {
    fun getSalesDaily(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<SalesDaily> {
        return salesDailyRepository.findAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }

    fun getSalesDaily(
        brandNo: Long,
        countryNo: Long
    ): List<SalesDaily> {
        return salesDailyRepository.findAllByBrandNoAndCountryNo(brandNo = brandNo, countryNo = countryNo)
    }

    fun getSalesDailyResponse(
        brandNo: Long,
        countryNo: Long
    ): List<SalesDailyResponse> {
        val campaignDaily = getCampaignDailyService.getCampaignDaily(brandNo = brandNo, countryNo = countryNo)
        return getSalesDaily(brandNo = brandNo, countryNo = countryNo).map { salesDaily ->
            val campaign = campaignDaily.singleOrNull { it.camaignDate == salesDaily.purchaseDate }
            SalesDailyResponse(salesDaily = salesDaily, campaignDaily = campaign)
        }.sortedBy { it.purchaseDate }.toList()
    }
}