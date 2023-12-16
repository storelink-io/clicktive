package com.clicktive.domains.api.data.dto.member

import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.framework.springframework.annotation.DescriptionFromEntity
import com.clicktive.framework.springframework.annotation.NoArg
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

import java.time.LocalDateTime
import java.util.Date

@NoArg
@DescriptionFromEntity(Member::class)
data class MemberResponse (
    val memberNo: Long?, //회원 번호
    val memberId: String?, //회원 ID
    val memberName: String?, //회원 명
    val memberStateCd: String, //회원 상태 코드
//    val memberStateCdName: String, //회원 상태 코드 명
    val memberTypeCd: String, //회원 유형 코드
//    val memberTypeCdName: String, //회원 유형 코드 명
//    val memberPw: String, //비밀번호
    val mobile: String?, //휴대폰 번호
    val companyNo: Long?,
    val jobPosition: String?,

    @JsonProperty("token")
    var accessToken: String?,
    @JsonIgnore
    var tokenDueDt: Date?,
//    @JsonIgnore
    var refreshToken: String?,
    @JsonIgnore
    var refreshTokenDueDt: Date
)
