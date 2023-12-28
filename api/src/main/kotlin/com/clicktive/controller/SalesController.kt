package com.clicktive.controller

import com.clicktive.domains.api.data.dto.SalesProductResponse
import com.clicktive.domains.api.data.dto.ad.*
import com.clicktive.domains.api.service.ad.*
import com.clicktive.framework.api.ApiResponse
import com.clicktive.framework.springframework.base.BaseController
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ctv/v1/sales")
class SalesController(
    private val getSalesMonthlyService: GetSalesMonthlyService,
    private val getSalesDailyService: GetSalesDailyService,
    private val getSalesProductService: GetSalesProductService
) : BaseController() {
    @Operation(summary = "일자별 판매 성과 - 판매 성과 (요약)")
    @GetMapping("/summary")
    fun getSummary(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String
    ): ResponseEntity<ApiResponse<SaleMonthlyResponse>> {
        val saleMonthlyResponse =
            getSalesMonthlyService.getSaleMonthlyResponse(brandNo = brandNo, countryNo = countryNo, month = month)
        return ResponseEntity
            .ok()
            .body(httpResponse(saleMonthlyResponse))
    }

    @Operation(summary = "일자별 판매 성과 - 일자별 판매 성과")
    @GetMapping("/daily")
    fun getDaily(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long
    ): ResponseEntity<ApiResponse<List<SalesDailyResponse>>> {
        val salesDailyResponse = getSalesDailyService.getSalesDailyResponse(brandNo = brandNo, countryNo = countryNo)
        return ResponseEntity
            .ok()
            .body(httpResponse(salesDailyResponse))
    }

    @Operation(summary = "상품별 판매 성과 - TOP 5")
    @GetMapping("/products/top")
    fun getProductsTop(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String
    ): ResponseEntity<ApiResponse<List<SalesProductTop5Response>>> {
        val salesProductTop5Response =
            getSalesProductService.getSalesProductTop5Response(brandNo = brandNo, countryNo = countryNo, month = month)
        return ResponseEntity
            .ok()
            .body(httpResponse(salesProductTop5Response))
    }

    @Operation(summary = "상품별 판매 성과 - 상품 성과")
    @GetMapping("/products")
    fun getProducts(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String
    ): ResponseEntity<ApiResponse<List<SalesProductResponse>>> {
        val salesProductResponse =
            getSalesProductService.getSalesProductResponse(brandNo = brandNo, countryNo = countryNo, month = month)
        return ResponseEntity
            .ok()
            .body(httpResponse(salesProductResponse))
    }

    @Operation(summary = "상품별 판매 성과 - 상품 판매 그래프")
    @GetMapping("/products/product")
    fun getProductsProduct(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String
    ): ResponseEntity<ApiResponse<List<SalesProductMonthlyResponse>>> {
        val salesProductMonthlyResponse =
            getSalesProductService.getSalesProductMonthlyResponse(brandNo = brandNo, countryNo = countryNo)
        return ResponseEntity
            .ok()
            .body(httpResponse(salesProductMonthlyResponse))
    }
}