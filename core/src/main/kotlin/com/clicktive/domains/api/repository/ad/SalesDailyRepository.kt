package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.SalesDaily
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SalesDailyRepository : JpaRepository<SalesDaily, Long> {
    fun findAllByBrandNoAndCountryNo(brandNo: Long, countryNo: Long): List<SalesDaily>
    fun findAllByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String): List<SalesDaily>
    fun deleteAllByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String)
}