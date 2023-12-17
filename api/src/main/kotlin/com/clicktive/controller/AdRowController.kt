package com.clicktive.controller

import com.clicktive.domains.api.data.dto.excel.ReadExcelRequestDto
import com.clicktive.domains.api.service.excel.ReadRawSalesExcelService
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

@RestController
@RequestMapping("/ctv/v1/ad/row")
class AdRowController(
    private val readRawSalesExcelService: ReadRawSalesExcelService
) : BaseController() {
    @Operation(summary = "판매 데이터 - 매출")
    @PostMapping("/sales")
    fun sales(
        @RequestParam file: MultipartFile,
        @Valid @RequestBody readExcelRequestDto: ReadExcelRequestDto
    ): ResponseEntity<ApiResponse<Int>> {
        val result = readRawSalesExcelService.readRawSales(
            file = file,
            readExcelRequestDto = readExcelRequestDto
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(result))
    }

    @Operation(summary = "판매 데이터 - 상품")
    @PostMapping("/sales/product")
    fun salesProduct(
        @RequestParam file: MultipartFile
    ) {

    }

    @Operation(summary = "광고 데이터 - 상품")
    @PostMapping("/campaign/product")
    fun campaignProduct(
        @RequestParam file: MultipartFile
    ) {

    }

    @Operation(summary = "광고 데이터 - 캠페인")
    @PostMapping("/campaign")
    fun campaign(
        @RequestParam file: MultipartFile
    ) {

    }

    @Operation(summary = "광고 데이터 - 키워드")
    @PostMapping("/campaign/keyword")
    fun campaignKeyword(
        @RequestParam file: MultipartFile
    ) {

    }

    @Operation(summary = "주문 처리")
    @PostMapping("/order")
    fun order(
        @RequestParam file: MultipartFile
    ) {

    }

    @Operation(summary = "재고 현황")
    @PostMapping("/stock")
    fun stock(
        @RequestParam file: MultipartFile
    ) {

    }
}