package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.RawSales
import com.clicktive.domains.api.repository.ad.RawSalesRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SalesService(
    private val rawSalesRepository: RawSalesRepository
) {
    @Transactional
    fun createRawSales(rawSales: List<RawSales>): List<RawSales> {
        deleteRawSales(
            brandNo = rawSales.first().brandNo,
            countryNo = rawSales.first().countryNo,
            month = rawSales.first().month
        )
        return rawSalesRepository.saveAll(rawSales)
    }

    fun deleteRawSales(
        brandNo: Long,
        countryNo: Long,
        month: String
    ) {
        rawSalesRepository.deleteAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}