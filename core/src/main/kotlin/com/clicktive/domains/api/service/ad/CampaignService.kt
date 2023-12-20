package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.RawCampaign
import com.clicktive.domains.api.repository.ad.RawCampaignRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CampaignService(
    private val rawCampaignRepository: RawCampaignRepository
) {
    @Transactional
    fun createRawCampaign(rawCampaign: List<RawCampaign>): List<RawCampaign> {
        deleteRawCampaign(
            brandNo = rawCampaign.first().brandNo,
            countryNo = rawCampaign.first().countryNo,
            month = rawCampaign.first().month
        )
        return rawCampaignRepository.saveAll(rawCampaign)
    }

    fun deleteRawCampaign(
        brandNo: Long,
        countryNo: Long,
        month: String
    ) {
        rawCampaignRepository.deleteAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}