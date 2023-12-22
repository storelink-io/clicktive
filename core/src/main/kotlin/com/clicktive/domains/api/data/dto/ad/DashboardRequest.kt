package com.clicktive.domains.api.data.dto.ad

import io.swagger.v3.oas.annotations.media.Schema

data class DashboardRequest(
    @Schema(description = "브랜드 번호", required = true)
    val brandNo: Long,
    @Schema(description = "국가 번호", required = true)
    val countryNo: Long,
    @Schema(description = "대상 월", required = true)
    val month: String,
    @Schema(description = "월간 운영 코멘트", required = false)
    val operationMemo: String?,
    @Schema(description = "스토어 성과 및 광고 코멘트", required = false)
    val storeAdMemo: String?,
    @Schema(description = "캠페인 결과 코멘트", required = false)
    val campaignMemo: String?,
    @Schema(description = "판매 상품 코멘트", required = false)
    val salesMemo: String?,
    @Schema(description = "키워드 광고 코멘트", required = false)
    val adKeywordMemo: String?
)
