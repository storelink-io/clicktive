package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.RawCampaign
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RawCampaignRepository : JpaRepository<RawCampaign, Long> {
}