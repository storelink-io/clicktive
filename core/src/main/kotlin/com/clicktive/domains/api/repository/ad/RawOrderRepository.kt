package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.RawOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RawOrderRepository : JpaRepository<RawOrder, Long> {
    fun deleteAllByBrandNoAndCountryNoAndMonth(brandNo: Long, countryNo: Long, month: String)
}