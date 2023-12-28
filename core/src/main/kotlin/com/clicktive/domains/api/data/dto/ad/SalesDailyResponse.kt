package com.clicktive.domains.api.data.dto.ad

import com.clicktive.domains.api.data.entity.ad.CampaignDaily
import com.clicktive.domains.api.data.entity.ad.SalesDaily
import io.swagger.v3.oas.annotations.media.Schema

data class SalesDailyResponse(
    @Schema(description = "일자", required = false)
    val purchaseDate: String?,
    @Schema(description = "매출", required = true)
    val salesAmt: Float,
    @Schema(description = "주문건수", required = true)
    val orderQty: Int,
    @Schema(description = "구매전환율", required = true)
    val customerUnitAmt: Float,
    @Schema(description = "환불률", required = true)
    val refundRate: Float,
    @Schema(description = "광고비", required = true)
    val adExpenseAmt: Float,
    @Schema(description = "광고매출", required = true)
    val adSalesAmt: Float,
    @Schema(description = "오가닉매출", required = true)
    val organicSalesAmt: Float
) {
    // TODO 두 테이블을 같이 쓰는 게 맞는지?
    // TODO null 허용?
    constructor(salesDaily: SalesDaily, campaignDaily: CampaignDaily?) : this(
        purchaseDate = salesDaily.purchaseDate,
        salesAmt = salesDaily.salesAmt,
        orderQty = salesDaily.orderQty,
        customerUnitAmt = salesDaily.customerUnitAmt,
        refundRate = salesDaily.refundRate,
        adExpenseAmt = campaignDaily?.adExpenseAmt ?: 0.toFloat(),
        adSalesAmt = campaignDaily?.adSalesAmt ?: 0.toFloat(),
        organicSalesAmt = campaignDaily?.organicSalesAmt ?: 0.toFloat()
    )
}