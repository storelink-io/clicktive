package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.repository.ad.RawCampaignProductRepository
import org.springframework.stereotype.Service

@Service
class GetCampaignProductService(
    private val rawCampaignProductRepository: RawCampaignProductRepository
) {
}