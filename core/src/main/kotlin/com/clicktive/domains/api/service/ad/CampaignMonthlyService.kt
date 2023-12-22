package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.CampaignMonthly
import com.clicktive.domains.api.repository.ad.CampaignMonthlyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CampaignMonthlyService(
    private val campaignMonthlyRepository: CampaignMonthlyRepository
) {
    @Transactional
    fun createCampaignMonthly(campaignMonthly: CampaignMonthly): CampaignMonthly {
        deleteCampaignMonthly(
            brandNo = campaignMonthly.brandNo,
            countryNo = campaignMonthly.countryNo,
            month = campaignMonthly.month
        )
        return campaignMonthlyRepository.save(campaignMonthly)
    }

    fun deleteCampaignMonthly(
        brandNo: Long,
        countryNo: Long,
        month: String
    ) {
        campaignMonthlyRepository.deleteByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}