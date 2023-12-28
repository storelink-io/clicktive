package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.CampaignDaily
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CampaignDailyRepository : JpaRepository<CampaignDaily, Long> {
    fun findAllByBrandNoAndCountryNo(brandNo: Long, countryNo: Long): List<CampaignDaily>
    fun findAllByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String): List<CampaignDaily>
    fun deleteAllByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String)
}