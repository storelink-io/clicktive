package com.clicktive.domains.api.data.dto.ad

import io.swagger.v3.oas.annotations.media.Schema

data class SalesResponse(
    @Schema(description = "매출 금액", required = true)
    val salesAmt: DashboardValue<Float>,
    @Schema(description = "주문건수", required = true)
    val orderQty: DashboardValue<Int>,
    @Schema(description = "객단가", required = true)
    val customerUnitAmt: DashboardValue<Float>,
    @Schema(description = "방문객수", required = true)
    val visitorNum: DashboardValue<Int>,
    @Schema(description = "구매전환율", required = true)
    val conversionRate: DashboardValue<Float>,
    @Schema(description = "환불률", required = true)
    val refundRate: DashboardValue<Float>
)