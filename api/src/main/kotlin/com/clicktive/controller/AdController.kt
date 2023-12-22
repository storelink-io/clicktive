package com.clicktive.controller

import com.clicktive.domains.api.data.dto.ad.ResultFileRequest
import com.clicktive.domains.api.data.dto.ad.ResultFileRespone
import com.clicktive.domains.api.service.ad.GetResultFileService
import com.clicktive.domains.api.service.ad.AdResultService
import com.clicktive.framework.api.ApiResponse
import com.clicktive.framework.springframework.base.BaseController
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ctv/v1/ad")
class AdController(
    private val getResultFileService: GetResultFileService,
    private val adResultService: AdResultService
) : BaseController() {
    @Operation(summary = "업로드 파일 상태 조회")
    @GetMapping("/result")
    fun getResult(
        @RequestParam("brandNo", required = true) brandNo: Long,
        @RequestParam("countryNo", required = true) countryNo: Long,
        @RequestParam("month", required = true) month: String
    ): ResponseEntity<ApiResponse<List<ResultFileRespone>>> {
        val resultFileRespone = getResultFileService.getResultFileRespone(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month
        )
        return ResponseEntity
            .ok()
            .body(httpResponse(resultFileRespone))
    }

    @Operation(summary = "성과 정보 저장")
    @PostMapping("/result")
    fun saveResult(
        @Valid @RequestBody resultFileRequest: ResultFileRequest
    ): ResponseEntity<ApiResponse<Unit>> {
        return ResponseEntity
            .ok()
            .body(httpResponse(adResultService.save(resultFileRequest)))
    }
}