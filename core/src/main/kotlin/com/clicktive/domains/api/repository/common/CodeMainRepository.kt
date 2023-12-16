package com.clicktive.domains.api.repository.common

import com.clicktive.domains.api.data.entity.common.CodeMain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SalesMonthlyRepository: JpaRepository<CodeMain, String>
{
}