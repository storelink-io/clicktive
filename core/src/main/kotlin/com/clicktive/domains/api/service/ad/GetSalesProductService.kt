package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.dto.SalesProductResponse
import com.clicktive.domains.api.data.dto.ad.SalesProductMonthlyResponse
import com.clicktive.domains.api.data.dto.ad.SalesProductTop10Response
import com.clicktive.domains.api.data.dto.ad.SalesProductTop5Response
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

    fun getRawSalesProduct(
        brandNo: Long,
        countryNo: Long
    ): List<RawSalesProduct> {
        return rawSalesProductRepository.findAllByBrandNoAndCountryNo(
            brandNo = brandNo,
            countryNo = countryNo
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

    fun getSalesProductTop5Response(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<SalesProductTop5Response> {
        val rawSalesProduct = getRawSalesProduct(brandNo = brandNo, countryNo = countryNo, month = month)
        val totalOrderQty = rawSalesProduct.sumOf { it.orderQty }
        val totalSalesAmt = rawSalesProduct.sumOf { it.salesAmt }

        return rawSalesProduct.sortedByDescending { it.orderQty }.take(5).mapIndexed { index, it ->
            SalesProductTop5Response(
                rank = (index + 1),
                totalOrderQty = totalOrderQty,
                totalSalesAmt = totalSalesAmt,
                rawSalesProduct = it
            )
        }
    }

    fun getSalesProductResponse(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<SalesProductResponse> {
        return getRawSalesProduct(brandNo = brandNo, countryNo = countryNo, month = month).map {
            SalesProductResponse(it)
        }
    }

    fun getSalesProductMonthlyResponse(
        brandNo: Long,
        countryNo: Long
    ): List<SalesProductMonthlyResponse> {
        val rawSalesProduct = getRawSalesProduct(brandNo = brandNo, countryNo = countryNo)

        return rawSalesProduct.groupBy { it.month }.map { (month, salesProduct) ->
            SalesProductMonthlyResponse(
                month = month,
                rawSalesProduct = salesProduct
            )
        }.sortedBy { it.month }
    }
}