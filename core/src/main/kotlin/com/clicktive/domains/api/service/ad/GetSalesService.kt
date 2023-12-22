package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.RawSales
import com.clicktive.domains.api.repository.ad.RawSalesRepository
import org.springframework.stereotype.Service

@Service
class GetSalesService(
    private val rawSalesRepository: RawSalesRepository
) {
    fun getRawSales(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<RawSales> {
        return rawSalesRepository.findAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}