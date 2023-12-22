package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.SalesMonthly
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SalesMonthlyRepository : JpaRepository<SalesMonthly, Long> {
    fun deleteByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String)
}