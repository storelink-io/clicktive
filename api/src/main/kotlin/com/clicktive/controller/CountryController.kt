package com.clicktive.controller

import com.clicktive.domains.api.data.dao.country.CountryDao
import com.clicktive.domains.api.data.dto.country.CountryRequest
import com.clicktive.domains.api.data.dto.country.CountryResponse
import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.domains.api.repository.member.CountryRepository
import com.clicktive.domains.api.service.country.CountryService
import com.clicktive.framework.api.ApiResponse
import com.clicktive.framework.springframework.annotation.CurrentMember
import com.clicktive.framework.springframework.base.BaseController
import com.clicktive.framework.util.Mapper
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ctv/v1/countries")
class CountryController(
    private val countryRepository: CountryRepository,
    private val countryService: CountryService,
    private val countryDao: CountryDao
): BaseController() {
    @GetMapping("/coutries")
    @Operation(
        summary = "국가 정보 검색"
    )
    fun findAllCounties(
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<List<CountryResponse>> {
        val countries = countryRepository.findAllByCountryStateCd()
        val tempResponse: List<CountryResponse> = Mapper.convertAll(countries)
        return httpResponse(tempResponse)
    }

    @PostMapping("country", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    @Operation(
        summary = "국가 정보 등록 및 수정"
    )
    fun saveCountry(
        @ModelAttribute countryRequest: CountryRequest,
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<Nothing> {
        countryService.saveCountry(countryRequest, currentMember.memberNo!!)
        return httpResponse()
    }

    @GetMapping("/country")
    @Operation(
        summary = "국가 상세 정보"
    )
    fun getCountry(
        countryNo: Long,
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<CountryResponse> {
        val country = countryRepository.getReferenceById(countryNo)
        val tempResponse: CountryResponse = Mapper.convert(country)
        return httpResponse(tempResponse)
    }

    @GetMapping("/all")
    @Operation(
        summary = "전체 국가 정보 리스트"
    )
    fun getCountries(
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<List<CountryResponse>> {
        val countries = countryRepository.findAllByCountryStateCd()
        val tempResponse: List<CountryResponse> = Mapper.convertAll(countries)
        return httpResponse(tempResponse)
    }

    @GetMapping("/brand")
    @Operation(
        summary = "브랜드별 국가 정보 리스트"
    )
    fun getBrandCountries(
        brandNo: Long,
        @Parameter(hidden = true) @CurrentMember currentMember: Member
    ): ApiResponse<List<CountryResponse>> {
        val countries = countryDao.findBrandCountries(brandNo)
        val tempResponse: List<CountryResponse> = Mapper.convertAll(countries)
        return httpResponse(tempResponse)
    }
}