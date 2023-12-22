package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.repository.ad.CampaignDailyRepository
import org.springframework.stereotype.Service

@Service
class GetCampaignDailyService(
    private val campaignDailyRepository: CampaignDailyRepository
) {
}