package com.clicktive.domains.api.repository.member

import com.clicktive.domains.api.data.entity.member.BrandCountry
import com.clicktive.domains.api.data.entity.member.BrandCountryPk
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface BrandCountryRepository: JpaRepository<BrandCountry, BrandCountryPk>
{
}