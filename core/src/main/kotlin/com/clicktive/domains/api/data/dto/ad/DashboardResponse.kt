package com.clicktive.domains.api.data.dto.ad

import com.clicktive.domains.api.data.entity.ad.AdDashboard
import io.swagger.v3.oas.annotations.media.Schema

data class DashboardResponse(
    @Schema(description = "광고 대시보드 번호", required = false)
    val adDashboardNo: Long?,
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
) {
    constructor(brandNo: Long, countryNo: Long, month: String) : this(
        adDashboardNo = null,
        brandNo = brandNo,
        countryNo = countryNo,
        month = month,
        operationMemo = null,
        storeAdMemo = null,
        campaignMemo = null,
        salesMemo = null,
        adKeywordMemo = null
    )

    constructor(adDashboard: AdDashboard) : this(
        adDashboardNo = adDashboard.adDashboardNo,
        brandNo = adDashboard.brandNo,
        countryNo = adDashboard.countryNo,
        month = adDashboard.month,
        operationMemo = adDashboard.operationMemo,
        storeAdMemo = adDashboard.storeAdMemo,
        campaignMemo = adDashboard.campaignMemo,
        salesMemo = adDashboard.salesMemo,
        adKeywordMemo = adDashboard.adKeywordMemo
    )
}
