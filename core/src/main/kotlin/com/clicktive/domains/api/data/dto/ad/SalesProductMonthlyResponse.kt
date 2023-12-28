package com.clicktive.domains.api.data.dto.ad

import com.clicktive.domains.api.data.entity.ad.RawSalesProduct
import io.swagger.v3.oas.annotations.media.Schema

data class SalesProductMonthlyResponse(
    @Schema(description = "대상 월", required = true)
    val month: String,
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
    constructor(month: String, rawSalesProduct: List<RawSalesProduct>): this(
        month = month,
        salesAmt = rawSalesProduct.sumOf { it.salesAmt },
        orderQty = rawSalesProduct.sumOf { it.orderQty },
        viewNum = 0,
        pageViewTotalNum = rawSalesProduct.sumOf { it.pageViewTotalNum }
    )
}