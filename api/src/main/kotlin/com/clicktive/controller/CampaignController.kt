package com.clicktive.controller

import com.clicktive.domains.api.data.dto.ad.*
import com.clicktive.domains.api.service.ad.*
import com.clicktive.framework.api.ApiResponse
import com.clicktive.framework.springframework.base.BaseController
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ctv/v1/campaigns")
class CampaignController(
    private val getCampaignService: GetCampaignService,
    private val getCampaignKeywordService: GetCampaignKeywordService,
    private val getCampaignProductService: GetCampaignProductService
) : BaseController() {
    @Operation(summary = "유형별 광고 성과 - 성과 요약")
    @GetMapping("/summary")
    fun getSummary(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String,
        @RequestParam("campaignTypeCd", required = false, defaultValue = "") campaignTypeCd: String,
        @RequestParam("portfolioName", required = false, defaultValue = "") portfolioName: String,
        @RequestParam("campaignName", required = false, defaultValue = "") campaignName: String
    ): ResponseEntity<ApiResponse<CampaignSummeryResponse>> {
        val campaignSummeryResponse = getCampaignService.getCampaignSummeryResponse(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            campaignTypeCd = campaignTypeCd,
            portfolioName = portfolioName,
            campaignName = campaignName
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(campaignSummeryResponse))
    }

    @Operation(summary = "유형별 광고 성과 - 유형별 성과")
    @GetMapping("/campaign-type")
    fun getCampaignType(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String,
        @RequestParam("campaignTypeCd", required = false, defaultValue = "") campaignTypeCd: String,
        @RequestParam("portfolioName", required = false, defaultValue = "") portfolioName: String,
        @RequestParam("campaignName", required = false, defaultValue = "") campaignName: String
    ): ResponseEntity<ApiResponse<List<RawCampaignResponse>>> {
        val rawCampaignResponse = getCampaignService.getRawCampaignResponse(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            campaignTypeCd = campaignTypeCd,
            portfolioName = portfolioName,
            campaignName = campaignName
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(rawCampaignResponse))
    }

    @Operation(summary = "유형별 광고 성과 - 유형별 성과")
    @GetMapping("/campaign-type/excel")
    fun getCampaignTypeExcel(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String
    ): ResponseEntity<ApiResponse<String>> {
        return ResponseEntity
            .ok()
            .body(httpResponse(""))
    }

    @Operation(summary = "키워드 성과 - 키워드별 성과")
    @GetMapping("/keywords")
    fun getKeywords(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String,
        @ModelAttribute rawCampaignKeywordRequest: RawCampaignKeywordRequest
    ): ResponseEntity<ApiResponse<List<RawCampaignKeywordResponse>>> {
        val rawCampaignKeywordResponse = getCampaignKeywordService.getRawCampaignKeywordResponse(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            rawCampaignKeywordRequest = rawCampaignKeywordRequest
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(rawCampaignKeywordResponse))
    }

    @Operation(summary = "키워드 성과 - 키워드별 성과")
    @GetMapping("/keywords/excel")
    fun getKeywordsExcel(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String
    ): ResponseEntity<ApiResponse<String>> {
        return ResponseEntity
            .ok()
            .body(httpResponse(""))
    }

    @Operation(summary = "키워드 성과 - 키워드별 성과 상세")
    @GetMapping("/keywords/keyword")
    fun getKeywordsKeyword(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("keyword", required = true) keyword: String
    ): ResponseEntity<ApiResponse<List<RawCampaignKeywordMonthlyResponse>>> {
        val rawCampaignKeywordMonthlyResponse = getCampaignKeywordService.getRawCampaignKeywordMonthlyResponse(
            brandNo = brandNo,
            countryNo = countryNo,
            keyword = keyword
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(rawCampaignKeywordMonthlyResponse))
    }

    @Operation(summary = "상품별 광고 성과 - 상품별 광고 성과")
    @GetMapping("/products")
    fun getProducts(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String,
        @ModelAttribute rawCampaignProductRequest: RawCampaignProductRequest
    ): ResponseEntity<ApiResponse<List<RawCampaignProductResponse>>> {
        val rawCampaignProductResponse = getCampaignProductService.getRawCampaignProductResponse(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            rawCampaignProductRequest = rawCampaignProductRequest
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(rawCampaignProductResponse))
    }

    @Operation(summary = "상품별 광고 성과 - 상품별 광고 성과")
    @GetMapping("/products/excel")
    fun getProductsExcel(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String
    ): ResponseEntity<ApiResponse<String>> {
        return ResponseEntity
            .ok()
            .body(httpResponse(""))
    }

    @Operation(summary = "상품별 광고 성과 - 상품별 광고 성과 상세")
    @GetMapping("/products/product")
    fun getProductsProduct(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("asin", required = true) asin: String
    ): ResponseEntity<ApiResponse<List<RawCampaignProductMonthlyResponse>>> {
        val rawCampaignProductMonthlyResponse = getCampaignProductService.getRawCampaignProductMonthlyResponse(
            brandNo = brandNo,
            countryNo = countryNo,
            asin = asin
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(rawCampaignProductMonthlyResponse))
    }
}