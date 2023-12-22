package com.clicktive.domains.api.data.dto.ad

import io.swagger.v3.oas.annotations.media.Schema

data class ResultFileRequest(
    @Schema(description = "브랜드 번호", required = true)
    val brandNo: Long,
    @Schema(description = "국가 번호", required = true)
    val countryNo: Long,
    @Schema(description = "대상 월", required = true)
    val month: String
)