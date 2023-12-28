package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.CampaignDaily
import com.clicktive.domains.api.repository.ad.CampaignDailyRepository
import org.springframework.stereotype.Service

@Service
class GetCampaignDailyService(
    private val campaignDailyRepository: CampaignDailyRepository
) {
    fun getCampaignDaily(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<CampaignDaily> {
        return campaignDailyRepository.findAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }

    fun getCampaignDaily(
        brandNo: Long,
        countryNo: Long
    ): List<CampaignDaily> {
        return campaignDailyRepository.findAllByBrandNoAndCountryNo(brandNo = brandNo, countryNo = countryNo)
    }
}