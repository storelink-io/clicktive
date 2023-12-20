package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.RawSales
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RawSalesRepository : JpaRepository<RawSales, Long> {
    fun deleteAllByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String)
}