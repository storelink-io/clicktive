package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.repository.ad.CampaignMonthlyRepository
import org.springframework.stereotype.Service

@Service
class GetCampaignMonthlyService(
    private val campaignMonthlyRepository: CampaignMonthlyRepository
) {
}