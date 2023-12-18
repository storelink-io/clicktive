package com.clicktive.domains.api.data.dto.common

import io.swagger.v3.oas.annotations.media.Schema

data class CodeDetailResponse(
    @Schema(description = "메인 코드", required = true, example = "")
    val mainCode: String,
    @Schema(description = "상세 코드", required = true, example = "")
    val detailCode: String,
    @Schema(description = "상세 코드 이름", required = false, example = "")
    val detailCodeName: String,
    @Schema(description = "코드 설명 1", required = false, example = "")
    val codeDesc1: String? = null,
    @Schema(description = "코드 설명 2", required = false, example = "")
    val codeDesc2: String? = null,
    @Schema(description = "코드 설명 3", required = false, example = "")
    val codeDesc3: String? = null,
    @Schema(description = "사용 여부", required = false, example = "")
    val sortNo: Int,
    @Schema(description = "노출 순번", required = false, example = "")
    val useYn: String? = null
)