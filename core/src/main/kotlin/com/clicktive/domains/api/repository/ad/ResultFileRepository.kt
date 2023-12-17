package com.clicktive.domains.api.repository.ad

import com.clicktive.domains.api.data.entity.ad.ResultFile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResultFileRepository : JpaRepository<ResultFile, Long> {
}