package com.clicktive.domains.api.data.dto.member

import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.domains.api.data.enum.member.MemberTypeEnum
import com.clicktive.framework.springframework.annotation.DescriptionFromEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

@NoArg
@DescriptionFromEntity(Member::class)
data class LoginMemberRequest (
    @field:Email
    val memberId: String, //회원 ID
    @field:Size(min=6, message = "비밀번호는 최소 6자 이상입니다")
    val memberPw: String, //비밀번호
)

@NoArg
@DescriptionFromEntity(Member::class)
data class MemberRegisterRequest (
//    val memberNo: Long?, //회원 번호
    val memberTypeCd: MemberTypeEnum, //회원 유형 코드
    @field:Email
    val memberId: String, //회원 ID
    val memberName: String, //회원 명
    val memberPw: String, //비밀번호
    val confirmPw: String, //확인 비밀번호
    val mobile: String?, //휴대폰 번호
    val jobPosition: String?, //직책
)