package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.CampaignDaily
import com.clicktive.domains.api.repository.ad.CampaignDailyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CampaignDailyService(
    private val campaignDailyRepository: CampaignDailyRepository
) {
    @Transactional
    fun createCampaignDaily(campaignDaily: List<CampaignDaily>): List<CampaignDaily> {
        deleteCampaignDaily(
            brandNo = campaignDaily.first().brandNo,
            countryNo = campaignDaily.first().countryNo,
            month = campaignDaily.first().month
        )
        return campaignDailyRepository.saveAll(campaignDaily)
    }

    fun deleteCampaignDaily(
        brandNo: Long,
        countryNo: Long,
        month: String
    ) {
        campaignDailyRepository.deleteAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}