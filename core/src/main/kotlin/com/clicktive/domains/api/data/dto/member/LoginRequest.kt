package com.clicktive.domains.api.data.dto.member

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class LoginRequest(
    @field:Email
    val memberId: String,
    @field:Size(min=6, message = "비밀번호는 최소 6자 이상입니다")
    val memberPw: String,
)
