package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.CampaignDaily
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface CampaignDailyRepository: JpaRepository<CampaignDaily, Long>{
}