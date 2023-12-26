package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.RawSalesProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RawSalesProductRepository : JpaRepository<RawSalesProduct, Long> {
    fun findAllByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String): List<RawSalesProduct>
    fun deleteAllByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String)
}