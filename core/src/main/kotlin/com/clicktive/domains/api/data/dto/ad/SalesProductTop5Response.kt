package com.clicktive.domains.api.data.dto.ad

import com.clicktive.domains.api.data.entity.ad.RawSalesProduct
import io.swagger.v3.oas.annotations.media.Schema

data class SalesProductTop5Response(
    @Schema(description = "순위", required = true)
    val rank: Int,
    @Schema(description = "상품명", required = false)
    val productName: String?,
    @Schema(description = "상품 주문량", required = true)
    val orderQty: Int,
    @Schema(description = "상품 주문량(비율)", required = true)
    val orderQtyPercentage: Float,
    @Schema(description = "주문한 상품 판매량", required = true)
    val salesAmt: Int,
    @Schema(description = "주문한 상품 판매량(비율)", required = true)
    val salesAmtPercentage: Float
) {
    constructor(rank: Int, totalOrderQty: Int, totalSalesAmt: Int, rawSalesProduct: RawSalesProduct): this(
        rank = rank,
        productName = rawSalesProduct.productName,
        orderQty = rawSalesProduct.orderQty,
        orderQtyPercentage = (totalOrderQty.takeIf { it > 0 }?.let { rawSalesProduct.orderQty.toFloat().div(totalOrderQty.toFloat()).times(100) } ?: 0.toFloat()),
        salesAmt = rawSalesProduct.salesAmt,
        // TODO div zero check 함수로 빼기
        // int.div(int) 이거 무조건 0일 확률 99.9
        // 결과가 소수라서 그냥 0 처리함;
        salesAmtPercentage = (totalSalesAmt.takeIf { it > 0 }?.let { rawSalesProduct.salesAmt.toFloat().div(totalSalesAmt.toFloat()).times(100) } ?: 0.toFloat())
    )
}