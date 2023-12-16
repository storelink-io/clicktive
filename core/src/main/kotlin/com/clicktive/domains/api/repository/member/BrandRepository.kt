package com.clicktive.domains.api.repository.member

import com.clicktive.domains.api.data.entity.member.Brand
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BrandRepository: JpaRepository<Brand, Long>
{
}