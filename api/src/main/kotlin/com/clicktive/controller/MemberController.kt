package com.clicktive.controller

import com.clicktive.domains.api.data.dto.member.LoginRequest
import com.clicktive.domains.api.data.dto.member.MemberRegisterRequest
import com.clicktive.domains.api.data.dto.member.MemberResponse
import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.domains.api.process.member.SignInProcess
import com.clicktive.domains.api.repository.member.MemberRepository
import com.clicktive.domains.api.service.member.MemberService
import com.clicktive.framework.api.ApiResponse
import com.clicktive.framework.exception.ServiceException
import com.clicktive.framework.springframework.annotation.CurrentMember
import com.clicktive.framework.springframework.base.BaseController
import com.clicktive.framework.util.Mapper
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ctv/v1/members")
class MemberController(
    private val signInProcess: SignInProcess,
    private val memberService: MemberService,
    private val memberRepository: MemberRepository
) : BaseController() {
    @PostMapping("/sign-in", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    @Operation(
        summary = "회원 로그인"
    )
    fun loginMember(
        @Valid @ModelAttribute loginRequest: LoginRequest
    ): ResponseEntity<ApiResponse<MemberResponse>> {
        val memberResponse = signInProcess.doProcess(loginRequest.memberId, loginRequest.memberPw)
        return ResponseEntity
            .ok()
            .body(httpResponse(memberResponse))
    }

    @PostMapping("/sign-up", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    @Operation(
        summary = "회원 가입"
    )
    fun createMember(
        @Valid @ModelAttribute memberRegisterRequest: MemberRegisterRequest
    ): ApiResponse<Nothing> {
        memberService.createAndUpdateMember(memberRegisterRequest)
        return httpResponse()
    }

    @GetMapping("/me")
    @Operation(
        summary = "로그인한 회원 정보"
    )
    fun getMemberInfo(
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<MemberResponse> {
        val member = memberRepository.getByMemberNo(currentMember.memberNo) ?: throw ServiceException("MEM-001")
        val memberResponse = Mapper.convert<MemberResponse>(member)
        val excludeProperty = mutableListOf("token", "refreshToken", "tokenDueDt", "refreshTokenDueDt")
        return httpResponse(memberResponse, excludeProperty)
    }

    @GetMapping("/company")
    @Operation(
        summary = "회사에 소속된 회원 리스트"
    )
    fun getCompanyMembers(
        companyNo: Long,
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<MutableList<MemberResponse>> {
        val membersByCompany = memberRepository.getByCompany(companyNo)
        val memberResponse   = Mapper.convert<MutableList<MemberResponse>>(membersByCompany)
        return httpResponse(memberResponse)
    }
}