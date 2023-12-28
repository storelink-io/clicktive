package com.clicktive.domains.api.data.dto

import com.clicktive.domains.api.data.entity.ad.RawSalesProduct
import io.swagger.v3.oas.annotations.media.Schema

data class SalesProductResponse(
    @Schema(description = "ASIN", required = false)
    val asin: String?,
    @Schema(description = "상품명", required = false)
    val productName: String?,
    @Schema(description = "주문금액", required = true)
    val salesAmt: Int,
    @Schema(description = "주문 상품수", required = true)
    val orderQty: Int,
    // TODO 어떤 값을 어떻게 가져와야 할지 모르겠음
    @Schema(description = "노출", required = true)
    val viewNum: Int,
    @Schema(description = "페이지 조회", required = true)
    val pageViewTotalNum: Int
) {
    constructor(rawSalesProduct: RawSalesProduct): this(
        asin = rawSalesProduct.asin,
        productName = rawSalesProduct.productName,
        salesAmt = rawSalesProduct.salesAmt,
        orderQty = rawSalesProduct.orderQty,
        viewNum = 0,
        pageViewTotalNum = rawSalesProduct.pageViewTotalNum
    )
}