package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.RawStock
import com.clicktive.domains.api.repository.ad.RawStockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StockService(
    private val rawStockRepository: RawStockRepository
) {
    @Transactional
    fun createRawStock(rawStock: List<RawStock>): List<RawStock> {
        deleteRawStock(
            brandNo = rawStock.first().brandNo,
            countryNo = rawStock.first().countryNo,
            month = rawStock.first().month
        )
        return rawStockRepository.saveAll(rawStock)
    }

    fun deleteRawStock(
        brandNo: Long,
        countryNo: Long,
        month: String
    ) {
        rawStockRepository.deleteAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}