package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.RawCampaignProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RawCampaignProductRepository : JpaRepository<RawCampaignProduct, Long> {
    fun deleteAllByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String)
}