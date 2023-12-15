package com.clicktive.controller

import com.clicktive.domains.api.data.dto.member.LoginMemberRequest
import com.clicktive.domains.api.data.dto.member.MemberRegisterRequest
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ctv/v1/members")
class MemberController {
    @PostMapping("/signin", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    @Operation(
        description = "회원 로그인"
    )
    fun loginMember(
        @Valid @ModelAttribute req: LoginMemberRequest
    ) {

    }

    @PostMapping("/signup", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    @Operation(
        description = "회원 가입"
    )
    fun createMember(
        @Valid @ModelAttribute req: MemberRegisterRequest
    ) {

    }
}