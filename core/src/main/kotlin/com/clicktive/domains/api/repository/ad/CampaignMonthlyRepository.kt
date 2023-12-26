package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.CampaignMonthly
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CampaignMonthlyRepository : JpaRepository<CampaignMonthly, Long> {
    fun findByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String): CampaignMonthly?
    fun deleteByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String)
}