package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.CampaignMonthly
import com.clicktive.domains.api.repository.ad.CampaignMonthlyRepository
import org.springframework.stereotype.Service

@Service
class GetCampaignMonthlyService(
    private val campaignMonthlyRepository: CampaignMonthlyRepository
) {
    fun getCampaignMonthly(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): CampaignMonthly? {
        return campaignMonthlyRepository.findByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}