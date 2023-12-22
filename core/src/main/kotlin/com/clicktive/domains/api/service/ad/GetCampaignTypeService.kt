package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.repository.ad.CampaignTypeRepository
import org.springframework.stereotype.Service

@Service
class GetCampaignTypeService(
    private val campaignTypeRepository: CampaignTypeRepository
) {
}