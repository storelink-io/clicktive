package com.clicktive.domains.api.data.dto.member

import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.domains.api.data.enum.member.MemberStateEnum
import com.clicktive.domains.api.data.enum.member.MemberTypeEnum
import com.clicktive.framework.springframework.annotation.DescriptionFromEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.validation.constraints.Email

@NoArg
@DescriptionFromEntity(Member::class)
data class MemberRequest (
    val memberName: String?,
    val memberStateCd: MemberStateEnum?,
)