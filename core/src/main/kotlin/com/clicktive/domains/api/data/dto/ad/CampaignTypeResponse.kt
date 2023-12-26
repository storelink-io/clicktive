package com.clicktive.domains.api.data.dto.ad

import com.clicktive.domains.api.data.entity.ad.CampaignType
import io.swagger.v3.oas.annotations.media.Schema

data class CampaignTypeResponse(
    @Schema(description = "", required = true)
    val campaignTypeCd: String,
    @Schema(description = "", required = true)
    val adExpenseAmt: Float,
    @Schema(description = "", required = true)
    val adSalesAmt: Float,
    @Schema(description = "", required = true)
    val roas: Float,
    @Schema(description = "", required = true)
    val acos: Float,
    @Schema(description = "", required = true)
    val ctr: Float,
    @Schema(description = "", required = true)
    val cpc: Float
) {
    constructor(campaignType: CampaignType) : this(
        campaignTypeCd = campaignType.campaignTypeCd ?: "",
        adExpenseAmt = campaignType.adExpenseAmt,
        adSalesAmt = campaignType.adSalesAmt,
        roas = campaignType.roas,
        acos = campaignType.acos,
        ctr = campaignType.ctr,
        cpc = campaignType.cpc
    )

    constructor(campaignTypeCd: String) : this(
        campaignTypeCd = campaignTypeCd,
        adExpenseAmt = 0.toFloat(),
        adSalesAmt = 0.toFloat(),
        roas = 0.toFloat(),
        acos = 0.toFloat(),
        ctr = 0.toFloat(),
        cpc = 0.toFloat()
    )
}