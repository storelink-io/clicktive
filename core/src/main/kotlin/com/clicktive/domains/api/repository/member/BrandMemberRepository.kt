package com.clicktive.domains.api.repository.member

import com.clicktive.domains.api.data.entity.member.BrandMember
import com.clicktive.domains.api.data.entity.member.BrandMemberPk
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BrandMemberRepository : JpaRepository<BrandMember, BrandMemberPk> {
    @Query("select m from BrandMember m where m.brandNo = :brandNo and m.memberNo = :memberNo ")
    fun getBrandMember(brandNo: Long, memberNo: Long): BrandMember?
}