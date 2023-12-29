package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.dao.ad.RawCampaignProductDao
import com.clicktive.domains.api.data.dto.ad.RawCampaignProductMonthlyResponse
import com.clicktive.domains.api.data.dto.ad.RawCampaignProductResponse
import com.clicktive.domains.api.data.dto.ad.RawCampaignProductRequest
import org.springframework.stereotype.Service

@Service
class GetCampaignProductService(
    private val rawCampaignProductDao: RawCampaignProductDao,
    private val getSalesProductService: GetSalesProductService
) {
    fun getRawCampaignProductResponse(
        brandNo: Long,
        countryNo: Long,
        month: String,
        rawCampaignProductRequest: RawCampaignProductRequest
    ): List<RawCampaignProductResponse> {
        val rawCampaignProduct = rawCampaignProductDao.getRawCampaignProduct(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            rawCampaignProductRequest = rawCampaignProductRequest
        )

        val rawSalesProduct = getSalesProductService.getRawSalesProduct(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month
        )

        return rawCampaignProduct.map {
            val productName = rawSalesProduct.singleOrNull { salesProduct -> salesProduct.asin == it.asin }?.productName
            RawCampaignProductResponse(rawCampaignProduct = it, productName = productName)
        }.filter {
            filteringProductName(
                productName = it.productName,
                searchProductName = rawCampaignProductRequest.productName
            )
        }
    }

    private fun filteringProductName(
        productName: String?,
        searchProductName: String?
    ): Boolean {
        return if (!searchProductName.isNullOrEmpty()) {
            productName?.contains(searchProductName) == true
        } else true
    }

    fun getRawCampaignProductMonthlyResponse(
        brandNo: Long,
        countryNo: Long,
        asin: String
    ): List<RawCampaignProductMonthlyResponse> {
        return rawCampaignProductDao.getRawCampaignProductMonthly(
            brandNo = brandNo,
            countryNo = countryNo,
            asin = asin
        ).map {
            RawCampaignProductMonthlyResponse(it)
        }
    }
}