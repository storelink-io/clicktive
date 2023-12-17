package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.SalesDaily
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SalesDailyRepository : JpaRepository<SalesDaily, Long> {
}