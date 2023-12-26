package com.clicktive.controller

import com.clicktive.domains.api.data.dto.ad.CampaignResponse
import com.clicktive.domains.api.data.dto.ad.DashboardRequest
import com.clicktive.domains.api.data.dto.ad.DashboardResponse
import com.clicktive.domains.api.data.dto.ad.SalesResponse
import com.clicktive.domains.api.service.ad.DashboardService
import com.clicktive.domains.api.service.ad.GetCampaignService
import com.clicktive.domains.api.service.ad.GetDashboardService
import com.clicktive.domains.api.service.ad.GetSalesService
import com.clicktive.framework.api.ApiResponse
import com.clicktive.framework.springframework.base.BaseController
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ctv/v1/dashboard")
class DashboardController(
    private val getDashboardService: GetDashboardService,
    private val dashboardService: DashboardService,
    private val getSalesService: GetSalesService,
    private val getCampaignService: GetCampaignService
) : BaseController() {
    @Operation(summary = "대시보드 코멘트 조회")
    @GetMapping("/comment")
    fun getComment(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String
    ): ResponseEntity<ApiResponse<DashboardResponse>> {
        val dashboardResponse =
            getDashboardService.getDashboardResponse(brandNo = brandNo, countryNo = countryNo, month = month)
        return ResponseEntity
            .ok()
            .body(httpResponse(dashboardResponse))
    }

    @Operation(summary = "대시보드 코멘트 저장")
    @PostMapping("/comment")
    fun saveComment(
        @Valid @RequestBody dashboardRequest: DashboardRequest
    ): ResponseEntity<ApiResponse<DashboardResponse>> {
        val dashboardResponse =
            dashboardService.saveDashboard(dashboardRequest = dashboardRequest)
        return ResponseEntity
            .ok()
            .body(httpResponse(dashboardResponse))
    }

    @Operation(summary = "스토어 성과 요약")
    @GetMapping("/sales")
    fun getSales(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String
    ): ResponseEntity<ApiResponse<SalesResponse>> {
        val salesResponse = getSalesService.getSalesResponse(brandNo = brandNo, countryNo = countryNo, month = month)
        return ResponseEntity
            .ok()
            .body(httpResponse(salesResponse))
    }

    @Operation(summary = "광고 요약")
    @GetMapping("/ad")
    fun getAd(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String
    ): ResponseEntity<ApiResponse<CampaignResponse>> {
        val campaignResponse = getCampaignService.getCampaignResponse(brandNo = brandNo, countryNo = countryNo, month = month)
        return ResponseEntity
            .ok()
            .body(httpResponse(campaignResponse))
    }

    @Operation(summary = "캠페인 유형별 성과")
    @GetMapping("/campaign-type")
    fun getCampaignType(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String
    ): ResponseEntity<ApiResponse<String>> {

        return ResponseEntity
            .ok()
            .body(httpResponse(""))
    }

    @Operation(summary = "상품 TOP 10")
    @GetMapping("/products")
    fun getProducts(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String
    ): ResponseEntity<ApiResponse<String>> {

        return ResponseEntity
            .ok()
            .body(httpResponse(""))
    }

    @Operation(summary = "키워드 광고 TOP 10")
    @GetMapping("/campain/keywords")
    fun getCampainKeywords(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String
    ): ResponseEntity<ApiResponse<String>> {

        return ResponseEntity
            .ok()
            .body(httpResponse(""))
    }
}