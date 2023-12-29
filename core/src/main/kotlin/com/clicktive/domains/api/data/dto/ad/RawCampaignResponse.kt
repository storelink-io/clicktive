package com.clicktive.domains.api.data.dto.ad

import com.clicktive.domains.api.data.entity.ad.RawCampaign
import io.swagger.v3.oas.annotations.media.Schema

data class RawCampaignResponse(
    @Schema(description = "유형", required = false)
    var campaignTypeCd: String?,
    @Schema(description = "포트폴리오 이름", required = false)
    var portfolioName: String?,
    @Schema(description = "캠페인명", required = false)
    var campaignName: String?,
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
    constructor(rawCampaign: RawCampaign) : this(
        // TODO campaignTypeCd 어떻게 변환?
        campaignTypeCd = rawCampaign.campaignType,
        portfolioName = rawCampaign.portfolioName,
        campaignName = rawCampaign.campaignName,
        expenseAmt = rawCampaign.expenseAmt,
        salesAmt = rawCampaign.salesAmt,
        roas = rawCampaign.roas,
        acos = rawCampaign.acos,
        viewNum = rawCampaign.viewNum,
        clickNum = rawCampaign.clickNum,
        ctr = rawCampaign.ctr,
        cpc = rawCampaign.cpc
    )
}