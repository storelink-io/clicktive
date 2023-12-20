package com.clicktive.controller

import com.clicktive.domains.api.data.dto.excel.ReadExcelRequest
import com.clicktive.domains.api.service.excel.*
import com.clicktive.framework.api.ApiResponse
import com.clicktive.framework.springframework.base.BaseController
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

// TODO set createDt, createMemberNo
@RestController
@RequestMapping("/ctv/v1/ad/raw")
class AdRowController(
    private val readRawSalesExcelService: ReadRawSalesExcelService,
    private val readRawSalesProductExcelService: ReadRawSalesProductExcelService,
    private val readRawCampaignProductExcelService: ReadRawCampaignProductExcelService,
    private val readRawCampaignExcelService: ReadRawCampaignExcelService,
    private val readRawCampaignKeywordExcelService: ReadRawCampaignKeywordExcelService,
    private val readRawOrderExcelService: ReadRawOrderExcelService,
    private val readRawStockExcelService: ReadRawStockExcelService
) : BaseController() {
    @Operation(summary = "판매 데이터 - 매출")
    @PostMapping("/sales")
    fun sales(
        @RequestParam file: MultipartFile,
        @Valid @RequestBody readExcelRequest: ReadExcelRequest
    ): ResponseEntity<ApiResponse<Int>> {
        val rowNum = readRawSalesExcelService.readRawSales(
            file = file,
            readExcelRequest = readExcelRequest
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(rowNum))
    }

    @Operation(summary = "판매 데이터 - 상품")
    @PostMapping("/sales/product")
    fun salesProduct(
        @RequestParam file: MultipartFile,
        @Valid @RequestBody readExcelRequest: ReadExcelRequest
    ): ResponseEntity<ApiResponse<Int>> {
        val rowNum = readRawSalesProductExcelService.readRawSalesProduct(
            file = file,
            readExcelRequest = readExcelRequest
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(rowNum))
    }

    @Operation(summary = "광고 데이터 - 상품")
    @PostMapping("/campaign/product")
    fun campaignProduct(
        @RequestParam file: MultipartFile,
        @Valid @RequestBody readExcelRequest: ReadExcelRequest
    ): ResponseEntity<ApiResponse<Int>> {
        val rowNum = readRawCampaignProductExcelService.readRawCampaignProduct(
            file = file,
            readExcelRequest = readExcelRequest
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(rowNum))
    }

    @Operation(summary = "광고 데이터 - 캠페인")
    @PostMapping("/campaign")
    fun campaign(
        @RequestParam file: MultipartFile,
        @Valid @RequestBody readExcelRequest: ReadExcelRequest
    ): ResponseEntity<ApiResponse<Int>> {
        val rowNum = readRawCampaignExcelService.readRawCampaign(
            file = file,
            readExcelRequest = readExcelRequest
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(rowNum))
    }

    @Operation(summary = "광고 데이터 - 키워드")
    @PostMapping("/campaign/keyword")
    fun campaignKeyword(
        @RequestParam file: MultipartFile,
        @Valid @RequestBody readExcelRequest: ReadExcelRequest
    ): ResponseEntity<ApiResponse<Int>> {
        val rowNum = readRawCampaignKeywordExcelService.readRawCampaignKeyword(
            file = file,
            readExcelRequest = readExcelRequest
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(rowNum))
    }

    @Operation(summary = "주문 처리")
    @PostMapping("/order")
    fun order(
        @RequestParam file: MultipartFile,
        @Valid @RequestBody readExcelRequest: ReadExcelRequest
    ): ResponseEntity<ApiResponse<Int>> {
        val rowNum = readRawOrderExcelService.readRawOrder(
            file = file,
            readExcelRequest = readExcelRequest
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(rowNum))
    }

    @Operation(summary = "재고 현황")
    @PostMapping("/stock")
    fun stock(
        @RequestParam file: MultipartFile,
        @Valid @RequestBody readExcelRequest: ReadExcelRequest
    ): ResponseEntity<ApiResponse<Int>> {
        val rowNum = readRawStockExcelService.readRawStock(
            file = file,
            readExcelRequest = readExcelRequest
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(rowNum))
    }
}