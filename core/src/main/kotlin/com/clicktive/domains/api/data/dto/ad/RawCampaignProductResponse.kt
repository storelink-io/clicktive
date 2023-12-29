package com.clicktive.domains.api.data.dto.ad

import com.clicktive.domains.api.data.entity.ad.RawCampaignProduct
import io.swagger.v3.oas.annotations.media.Schema

data class RawCampaignProductResponse(
    @Schema(description = "ASIN", required = false)
    val asin: String?,
    @Schema(description = "상품 명", required = false)
    var productName: String?,
    @Schema(description = "광고비", required = true)
    val expenseAmt: Float,
    @Schema(description = "광고매출", required = true)
    val salesTotal7Amt: Float,
    @Schema(description = "ROAS", required = true)
    val roas: Float,
    @Schema(description = "ACOS", required = true)
    val acos: Float,
    @Schema(description = "노출수", required = true)
    val viewNum: Int,
    @Schema(description = "클릭수", required = true)
    val clickNum: Int,
    @Schema(description = "CTR", required = true)
    val ctr: Float,
    @Schema(description = "CPC", required = true)
    val cpc: Float
){
    constructor(rawCampaignProduct: RawCampaignProduct, productName: String?) : this(
        asin = rawCampaignProduct.asin,
        productName = productName,
        expenseAmt = rawCampaignProduct.expenseAmt,
        salesTotal7Amt = rawCampaignProduct.salesTotal7Amt,
        roas = rawCampaignProduct.roas,
        acos = rawCampaignProduct.acos,
        viewNum = rawCampaignProduct.viewNum,
        clickNum = rawCampaignProduct.clickNum,
        ctr = rawCampaignProduct.ctr,
        cpc = rawCampaignProduct.cpc
    )
}