package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.code.campaignTypeCd
import com.clicktive.domains.api.data.dto.ad.CampaignTypeResponse
import com.clicktive.domains.api.data.entity.ad.CampaignType
import com.clicktive.domains.api.repository.ad.CampaignTypeRepository
import org.springframework.stereotype.Service

@Service
class GetCampaignTypeService(
    private val campaignTypeRepository: CampaignTypeRepository
) {
    fun getCampaignType(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<CampaignType> {
        return campaignTypeRepository.findAllByBrandNoAndCountryNoAndMonth(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month
        )
    }

    fun getCampaignTypeResponse(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<CampaignTypeResponse> {
        val campaignType = getCampaignType(brandNo = brandNo, countryNo = countryNo, month = month)

        return campaignTypeCd.map { (detailCode, _) ->
            val ct = campaignType.singleOrNull { it.campaignTypeCd == detailCode }
            ct.takeIf { it != null }?.let { CampaignTypeResponse(campaignType = it) } ?: CampaignTypeResponse(campaignTypeCd = detailCode)
        }
    }
}