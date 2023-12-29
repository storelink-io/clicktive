package com.clicktive.domains.api.data.dto.ad

import org.springframework.web.bind.annotation.RequestParam

data class RawCampaignProductRequest(
    @RequestParam("asin", required = false)
    val asin: String?,
    @RequestParam("productName", required = false)
    val productName: String?,
    @RequestParam("minExpenseAmt", required = false)
    val minExpenseAmt: Float?,
    @RequestParam("maxExpenseAmt", required = false)
    val maxExpenseAmt: Float?,
    @RequestParam("minSalesTotal7Amt", required = false)
    val minSalesTotal7Amt: Float?,
    @RequestParam("maxSalesTotal7Amt", required = false)
    val maxSalesTotal7Amt: Float?,
    @RequestParam("minRoas", required = false)
    val minRoas: Float?,
    @RequestParam("maxRoas", required = false)
    val maxRoas: Float?,
    @RequestParam("minAcos", required = false)
    val minAcos: Float?,
    @RequestParam("maxAcos", required = false)
    val maxAcos: Float?,
    @RequestParam("minViewNum", required = false)
    val minViewNum: Int?,
    @RequestParam("maxViewNum", required = false)
    val maxViewNum: Int?,
    @RequestParam("minClickNum", required = false)
    val minClickNum: Int?,
    @RequestParam("maxClickNum", required = false)
    val maxClickNum: Int?,
    @RequestParam("minCtr", required = false)
    val minCtr: Float?,
    @RequestParam("maxCtr", required = false)
    val maxCtr: Float?,
    @RequestParam("minCpc", required = false)
    val minCpc: Float?,
    @RequestParam("maxCpc", required = false)
    val maxCpc: Float?
)