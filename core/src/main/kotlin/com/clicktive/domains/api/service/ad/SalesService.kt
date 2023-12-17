package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.RawSales
import com.clicktive.domains.api.repository.ad.RawSalesRepository
import org.springframework.stereotype.Service

@Service
class SalesService(
    private val rawSalesRepository: RawSalesRepository
) {
    fun createRawSales(rawSales: List<RawSales>) {
        rawSalesRepository.saveAll(rawSales)
    }
}