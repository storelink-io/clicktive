package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.RawCampaign
import com.clicktive.domains.api.repository.ad.RawCampaignRepository
import org.springframework.stereotype.Service

@Service
class GetCampaignService(
    private val rawCampaignRepository: RawCampaignRepository
) {
    fun getRawCampaign(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<RawCampaign> {
        return rawCampaignRepository.findAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}