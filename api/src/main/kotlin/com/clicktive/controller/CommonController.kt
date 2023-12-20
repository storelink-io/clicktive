package com.clicktive.controller

import com.clicktive.domains.api.data.dto.company.CompanyResponse
import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.domains.api.repository.common.CodeDetailRepository
import com.clicktive.framework.api.ApiResponse
import com.clicktive.framework.springframework.annotation.CurrentMember
import com.clicktive.framework.springframework.base.BaseController
import com.clicktive.framework.util.Mapper
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ctv/v1/commons")
class CommonController(
    private val codeDetailRepository: CodeDetailRepository,

): BaseController() {
    @GetMapping("/code")
    @Operation(
        summary = "코드 정보 리스트"
    )
    fun getCompanies(
        mainCode: String
    ): ApiResponse<MutableList<CompanyResponse>> {
        val codes = codeDetailRepository.getByMainCode(mainCode)
        val tempResponse = Mapper.convert<MutableList<CompanyResponse>>(codes)

        return httpResponse(tempResponse)
    }
}