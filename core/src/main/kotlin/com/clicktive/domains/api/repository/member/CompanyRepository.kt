package com.clicktive.domains.api.repository.member

import com.clicktive.domains.api.data.entity.member.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, Long> {
    @Query("select m from Company m where m.companyStateCd = :companyStateCd ")
    fun getByCompanyStateCd(companyStateCd: String = "100"): MutableList<Company>
}