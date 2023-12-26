package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.dto.ad.SalesProductTop10Response
import com.clicktive.domains.api.data.entity.ad.RawSalesProduct
import com.clicktive.domains.api.repository.ad.RawSalesProductRepository
import org.springframework.stereotype.Service

@Service
class GetSalesProductService(
    private val rawSalesProductRepository: RawSalesProductRepository
) {
    fun getRawSalesProduct(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<RawSalesProduct> {
        return rawSalesProductRepository.findAllByBrandNoAndCountryNoAndMonth(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month
        )
    }

    fun getSalesProductTop10Response(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<SalesProductTop10Response> {
        val rawSalesProduct = getRawSalesProduct(brandNo = brandNo, countryNo = countryNo, month = month)
        return rawSalesProduct.sortedByDescending { it.salesAmt }.take(10).mapIndexed { index, it ->
            SalesProductTop10Response(rank = (index + 1), rawSalesProduct = it)
        }
    }
}