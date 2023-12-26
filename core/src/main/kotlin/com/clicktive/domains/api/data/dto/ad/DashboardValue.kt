package com.clicktive.domains.api.data.dto.ad

import io.swagger.v3.oas.annotations.media.Schema

data class DashboardValue<T>(
    @Schema(description = "이전달 값", required = true)
    val previousMonthValue: T?,
    @Schema(description = "이번달 값", required = true)
    val currentMonthValue: T,
    @Schema(description = "이번달 한달 값", required = true)
    val currentMonthValues: Map<String, T>
)