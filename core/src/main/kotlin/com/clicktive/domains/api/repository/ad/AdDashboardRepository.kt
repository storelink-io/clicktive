package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.AdDashboard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdDashboardRepository : JpaRepository<AdDashboard, Long> {
    fun findByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String): AdDashboard?
}