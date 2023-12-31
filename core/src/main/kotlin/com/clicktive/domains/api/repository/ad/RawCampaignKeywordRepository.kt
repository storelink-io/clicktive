package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.RawCampaignKeyword
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RawCampaignKeywordRepository : JpaRepository<RawCampaignKeyword, Long> {
    fun findAllByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String): List<RawCampaignKeyword>
    fun deleteAllByBrandNoAndCountryNoAndMonth(brandNo: Int, countryNo: Int, month: String)
}