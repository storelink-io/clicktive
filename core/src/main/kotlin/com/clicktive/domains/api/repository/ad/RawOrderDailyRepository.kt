package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.RawOrderDaily
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RawOrderDailyRepository : JpaRepository<RawOrderDaily, Long> {
}