package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.RawOrder
import com.clicktive.domains.api.repository.ad.RawOrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val rawOrderRepository: RawOrderRepository
) {
    @Transactional
    fun createRawOrder(rawOrder: List<RawOrder>): List<RawOrder> {
        deleteRawOrder(
            brandNo = rawOrder.first().brandNo,
            countryNo = rawOrder.first().countryNo,
            month = rawOrder.first().month
        )
        return rawOrderRepository.saveAll(rawOrder)
    }

    fun deleteRawOrder(
        brandNo: Long,
        countryNo: Long,
        month: String
    ) {
        rawOrderRepository.deleteAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}