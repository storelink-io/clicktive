package com.clicktive.domains.api.data.dto.ad

import io.swagger.v3.oas.annotations.media.Schema

data class CampaignResponse(
    @Schema(description = "광고비", required = true)
    val adExpenseAmt: DashboardValue<Float>,
    @Schema(description = "광고매출", required = true)
    val adSalesAmt: DashboardValue<Float>,
    @Schema(description = "오가닉매출", required = true)
    val organicSalesAmt: DashboardValue<Float>,
    @Schema(description = "TOTAL ROAS", required = true)
    val totalRoas: DashboardValue<Float>,
    @Schema(description = "ROAS", required = true)
    val roas: DashboardValue<Float>,
    @Schema(description = "ACOS", required = true)
    val acos: DashboardValue<Float>,
    @Schema(description = "노출수", required = true)
    val viewNum: DashboardValue<Int>,
    @Schema(description = "클릭수", required = true)
    val clickNum: DashboardValue<Int>,
    @Schema(description = "PV", required = true)
    val pv: DashboardValue<Int>,
    @Schema(description = "CTR", required = true)
    val ctr: DashboardValue<Float>,
    @Schema(description = "CPC", required = true)
    val cpc: DashboardValue<Float>,
    @Schema(description = "CPM", required = true)
    val cpm: DashboardValue<Float>
)