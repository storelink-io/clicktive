package com.clicktive.domains.api.data.dto.member

import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.domains.api.data.enum.member.MemberStateEnum
import com.clicktive.domains.api.data.enum.member.MemberTypeEnum
import com.clicktive.framework.springframework.annotation.DescriptionFromEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.validation.constraints.Email

@NoArg
@DescriptionFromEntity(Member::class)
data class MemberRegisterRequestDto (
    val memberNo: Long?,
    val memberTypeCd: MemberTypeEnum,
    val memberStateCd: MemberStateEnum,
    @field:Email
    val memberId: String,
    val memberName: String,
    val memberPw: String,
    val confirmPw: String,
    val companyNo: Long,
    val mobile: String?,
    val jobPosition: String?,
)