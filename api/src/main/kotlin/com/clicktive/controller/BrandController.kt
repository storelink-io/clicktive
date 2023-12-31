package com.clicktive.controller

import com.clicktive.domains.api.data.dao.brand.BrandDao
import com.clicktive.domains.api.data.dto.brand.BrandMemberRequest
import com.clicktive.domains.api.data.dto.brand.BrandRequest
import com.clicktive.domains.api.data.dto.brand.BrandResponse
import com.clicktive.domains.api.data.dto.brand.SearchBrandRequest
import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.domains.api.repository.member.BrandRepository
import com.clicktive.domains.api.service.brand.BrandService
import com.clicktive.framework.api.ApiResponse
import com.clicktive.framework.springframework.annotation.CurrentMember
import com.clicktive.framework.springframework.base.BaseController
import com.clicktive.framework.util.Mapper
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ctv/v1/brands")
class BrandController(
    private val brandDao: BrandDao,
    private val brandService: BrandService
): BaseController() {
    @GetMapping("/brands")
    @Operation(
        summary = "브랜드 정보 검색"
    )
    fun findAllBrands(
        @ModelAttribute req: SearchBrandRequest,
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<List<BrandResponse>> {
        val brands = brandDao.getAllBrands(req.brandName, req.brandStateCd)
        val tempResponse: List<BrandResponse> = Mapper.convertAll(brands)
        return httpResponse(tempResponse, mutableListOf("modifyDt", "modifyMemberNo", "createMemberNo"))
    }

    @PostMapping("brand", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    @Operation(
        summary = "브랜드 정보 등록 및 수정"
    )
    fun saveCompany(
        @ModelAttribute req: BrandRequest,
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<Nothing> {
        brandService.saveBrand(req, currentMember.memberNo!!)
        return httpResponse()
    }


    @GetMapping("/member")
    @Operation(
        summary = "회원별 브랜드 정보 리스트"
    )
    fun getMemberBrands(
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<List<BrandResponse>> {
        val brands = brandDao.findMemberBrands(currentMember.memberNo!!)
        val tempResponse: List<BrandResponse> = Mapper.convertAll(brands)
        return httpResponse(tempResponse)
    }

    @PostMapping("/member", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    @Operation(
        summary = "브랜드 권한을 가질 회원 등록"
    )
    fun saveBrandMembers(
        @ModelAttribute req: BrandMemberRequest,
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<Nothing> {
        brandService.saveBrandMember(req, currentMember.memberNo!!)
        return httpResponse()
    }

    @GetMapping("/country")
    @Operation(
        summary = "국가별 브랜드 정보 리스트"
    )
    fun getCountryBrands(
        countryNo: Long,
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<List<BrandResponse>> {
        val brands = brandDao.findCountryBrands(countryNo)
        val tempResponse: List<BrandResponse> = Mapper.convertAll(brands)
        return httpResponse(tempResponse)
    }
}