package com.clicktive.domains.api.data.dto.ad

import com.clicktive.domains.api.data.entity.ad.RawCampaign
import io.swagger.v3.oas.annotations.media.Schema

data class CampaignSummeryResponse(
    @Schema(description = "광고비", required = true)
    val expenseAmt: Float,
    @Schema(description = "광고매출", required = true)
    val salesAmt: Float,
    @Schema(description = "ROAS", required = true)
    val roas: Int,
    @Schema(description = "ACOS", required = true)
    val acos: Int,
    @Schema(description = "노출수", required = true)
    val viewNum: Int,
    @Schema(description = "클릭수", required = true)
    val clickNum: Int,
    @Schema(description = "CTR", required = true)
    val ctr: Int,
    @Schema(description = "CPC", required = true)
    val cpc: Int
) {
    constructor(rawCampaign: List<RawCampaign>) : this(
        expenseAmt = rawCampaign.map { it.expenseAmt }.sum(),
        salesAmt = rawCampaign.map { it.salesAmt }.sum(),
        roas = rawCampaign.sumOf { it.roas },
        acos = rawCampaign.sumOf { it.acos },
        viewNum = rawCampaign.sumOf { it.viewNum },
        clickNum = rawCampaign.sumOf { it.clickNum },
        ctr = rawCampaign.sumOf { it.ctr },
        cpc = rawCampaign.sumOf { it.cpc }
    )
}