package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.CampaignType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CampaignTypeRepository : JpaRepository<CampaignType, Long> {
    fun findAllByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String): List<CampaignType>
    fun deleteAllByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String)
}