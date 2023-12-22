package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.CampaignType
import com.clicktive.domains.api.repository.ad.CampaignTypeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CampaignTypeService(
    private val campaignTypeRepository: CampaignTypeRepository
) {
    @Transactional
    fun createCampaignType(campaignType: List<CampaignType>): List<CampaignType> {
        deleteCampaignType(
            brandNo = campaignType.first().brandNo,
            countryNo = campaignType.first().countryNo,
            month = campaignType.first().month
        )
        return campaignTypeRepository.saveAll(campaignType)
    }

    fun deleteCampaignType(
        brandNo: Long,
        countryNo: Long,
        month: String
    ) {
        campaignTypeRepository.deleteAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}