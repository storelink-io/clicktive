package com.clicktive.domains.api.data.dto.ad

import com.clicktive.domains.api.data.entity.ad.RawSalesProduct
import io.swagger.v3.oas.annotations.media.Schema

data class SalesProductTop10Response(
    @Schema(description = "순위", required = true)
    val rank: Int,
    @Schema(description = "ASIN", required = false)
    val asin: String?,
    @Schema(description = "상품명", required = false)
    val productName: String?,
    @Schema(description = "주문금액", required = true)
    val salesAmt: Int,
    @Schema(description = "주문상품수", required = true)
    val orderQty: Int,
    @Schema(description = "노출", required = true)
    val viewNum: Int,
    @Schema(description = "페이지 조회", required = true)
    val pageViewTotalNum: Int
) {
    constructor(rank: Int, rawSalesProduct: RawSalesProduct): this(
        rank = rank,
        asin = rawSalesProduct.asin,
        productName = rawSalesProduct.productName,
        salesAmt = rawSalesProduct.salesAmt,
        orderQty = rawSalesProduct.orderQty,
        viewNum = 0,
        pageViewTotalNum = rawSalesProduct.pageViewTotalNum
    )
}