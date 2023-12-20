package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.RawStock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RawStockRepository : JpaRepository<RawStock, Long> {
    fun deleteAllByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String)
}