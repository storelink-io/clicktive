package com.clicktive.domains.api.data.dto.member

import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.framework.springframework.annotation.DescriptionFromEntity
import com.clicktive.framework.springframework.annotation.NoArg
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

import java.util.Date

@NoArg
@DescriptionFromEntity(Member::class)
data class MemberResponseDto (
    val memberNo: Long?,
    val memberId: String?,
    val memberName: String?,
    val memberStateCd: String,
    val memberTypeCd: String,
    val mobile: String?,
    val companyNo: Long?,
    val jobPosition: String?,

    @JsonProperty("token")
    var accessToken: String?,
    @JsonIgnore
    var tokenDueDt: Date?,
    var refreshToken: String?,
    @JsonIgnore
    var refreshTokenDueDt: Date
)
