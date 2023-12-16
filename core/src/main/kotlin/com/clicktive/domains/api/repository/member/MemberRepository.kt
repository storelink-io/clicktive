package com.clicktive.domains.api.repository.member

import com.clicktive.domains.api.data.entity.member.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: JpaRepository<Member, Long> {
    @Query("select main from Member main where main.memberNo = :memberNo and main.memberStateCd in ('100') ")
    fun getByMemberNo(memberNo: Long?): Member?

    fun getByMemberId(memberId: String): Member?
    fun findByMobile(mobile: String): List<Member>
}