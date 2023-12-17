package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.RawCampaignKeywordOrg
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RawCampaignKeywordOrgRepository : JpaRepository<RawCampaignKeywordOrg, Long> {
}