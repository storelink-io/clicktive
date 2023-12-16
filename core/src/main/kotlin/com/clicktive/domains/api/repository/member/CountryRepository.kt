package com.clicktive.domains.api.repository.member

import com.clicktive.domains.api.data.entity.member.Country
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CountryRepository: JpaRepository<Country, Long>{
}