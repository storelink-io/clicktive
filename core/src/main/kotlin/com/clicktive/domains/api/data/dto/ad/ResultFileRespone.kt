package com.clicktive.domains.api.data.dto.ad

import io.swagger.v3.oas.annotations.media.Schema

data class ResultFileRespone(
    @Schema(description = "성과 파일 유형 코드", required = true, example = "")
    val resultFileTypeCd: String,
    @Schema(description = "성과 파일 유형 코드 이름", required = true, example = "")
    val resultFileTypeCdName: String,
    @Schema(description = "업로드 여부", required = true, example = "")
    val isUploaded: Boolean
)
