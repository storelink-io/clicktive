package com.clicktive.domains.api.data.enum.ad

enum class ResultFileTypeCd(
    val detailCode: String
) {
    RAW_SALES(detailCode = "100"),
    RAW_SALES_PRODUCT(detailCode = "101"),
    RAW_CAMPAIGN(detailCode = "102"),
    RAW_CAMPAIGN_PRODUCT(detailCode = "103"),
    RAW_CAMPAIGN_KEYWORD(detailCode = "104"),
    RAW_ORDER(detailCode = "105"),
    RAW_STOCK(detailCode = "106")
}