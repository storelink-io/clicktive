package com.clicktive.domains.api.data.dto.ad

import com.clicktive.domains.api.data.entity.ad.SalesMonthly
import io.swagger.v3.oas.annotations.media.Schema

data class SaleMonthlyResponse(
    @Schema(description = "매출", required = true)
    val salesAmt: Float,
    @Schema(description = "주문건수", required = true)
    val orderQty: Int,
    @Schema(description = "객단가", required = true)
    val customerUnitAmt: Float,
    @Schema(description = "총 주문 아이템", required = true)
    val totalOrderItemQty: Int,
    @Schema(description = "상품 주문량", required = true)
    val totalOrderQty: Int,
    @Schema(description = "주문한 상품 판매량", required = true)
    val salesQty: Float,
    @Schema(description = "평균 상품/주문 아이템", required = true)
    val avgSalesQtyRate: Float,
    @Schema(description = "평균 판매/주문 아이템", required = true)
    val avgSalesAmtRate: Float
) {
    constructor(salesMonthly: SalesMonthly): this(
        salesAmt = salesMonthly.salesAmt,
        orderQty = salesMonthly.orderQty,
        customerUnitAmt = salesMonthly.customerUnitAmt,
        totalOrderItemQty = salesMonthly.totalOrderItemQty,
        totalOrderQty = salesMonthly.totalOrderQty,
        salesQty = salesMonthly.salesQty,
        avgSalesQtyRate = salesMonthly.avgSalesQtyRate,
        avgSalesAmtRate = salesMonthly.avgSalesAmtRate
    )
}