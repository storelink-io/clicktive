package com.clicktive.domains.api.data.dto.ad

import com.clicktive.domains.api.data.entity.ad.RawCampaignKeyword
import io.swagger.v3.oas.annotations.media.Schema

data class CampaignKeywordTop10Response(
    @Schema(description = "순위", required = true)
    val rank: Int,
    @Schema(description = "키워드명", required = false)
    val keyword: String?,
    @Schema(description = "광고비", required = true)
    val expenseAmt: Float,
    @Schema(description = "광고매출", required = true)
    val salesTotal7Amt: Float,
    @Schema(description = "ROAS", required = true)
    val roas: Float,
    @Schema(description = "ACOS", required = true)
    val acos: Float,
    @Schema(description = "노출", required = true)
    val viewNum: Int,
    @Schema(description = "클릭", required = true)
    val clickNum: Int,
    @Schema(description = "CTR", required = true)
    val ctr: Float,
    @Schema(description = "CPC", required = true)
    val cpc: Float
) {
    constructor(rank: Int, rawCampaignKeyword: RawCampaignKeyword) : this(
        rank = rank,
        keyword = rawCampaignKeyword.keyword,
        expenseAmt = rawCampaignKeyword.expenseAmt,
        salesTotal7Amt = rawCampaignKeyword.salesTotal7Amt,
        roas = rawCampaignKeyword.roas,
        acos = rawCampaignKeyword.acos,
        viewNum = rawCampaignKeyword.viewNum,
        clickNum = rawCampaignKeyword.clickNum,
        ctr = rawCampaignKeyword.ctr,
        cpc = rawCampaignKeyword.cpc,
    )
}