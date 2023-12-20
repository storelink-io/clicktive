package com.clicktive.controller

import com.clicktive.domains.api.data.dao.company.CompanyDao
import com.clicktive.domains.api.data.dto.company.CompanyRequest
import com.clicktive.domains.api.data.dto.company.CompanyResponse
import com.clicktive.domains.api.data.dto.company.SearchCompanyRequest
import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.domains.api.repository.member.CompanyRepository
import com.clicktive.domains.api.service.company.CompanyService
import com.clicktive.framework.api.ApiResponse
import com.clicktive.framework.springframework.annotation.CurrentMember
import com.clicktive.framework.springframework.base.BaseController
import com.clicktive.framework.util.Mapper
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ctv/v1/companies")
class CompanyController(
    private val companyRepository: CompanyRepository,
    private val companyService: CompanyService,
    private val companyDao: CompanyDao
): BaseController() {
    @GetMapping("/companies")
    @Operation(
        summary = "회사 정보 검색"
    )
    fun findAllCompanies(
        @ModelAttribute req: SearchCompanyRequest,
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<List<CompanyResponse>> {
        val companies = companyDao.getAllCompanies(req.companyName, req.companyStateCd)
        val tempResponse: MutableList<CompanyResponse> = Mapper.convert(companies)
        return httpResponse(tempResponse)
    }

    @PostMapping("company", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    @Operation(
        summary = "회사 정보 등록 및 수정"
    )
    fun saveCompany(
        @ModelAttribute req: CompanyRequest,
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<Nothing> {
        companyService.saveCompany(req, currentMember.memberNo!!)
        return httpResponse()
    }

    @GetMapping("/company")
    @Operation(
        summary = "회사 상세 정보"
    )
    fun getCompany(
        companyNo: Long,
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<CompanyResponse> {
        val company = companyRepository.getReferenceById(companyNo)
        val tempResponse: CompanyResponse = Mapper.convert(company)
        return httpResponse(tempResponse)
    }

    @GetMapping("/all")
    @Operation(
        summary = "전체 회사 정보 리스트"
    )
    fun getCompanies(
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<List<CompanyResponse>> {
        val companies = companyRepository.getByCompanyStateCd()
        val tempResponse: MutableList<CompanyResponse> = Mapper.convert(companies)
        return httpResponse(tempResponse)
    }
}