package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.RawOrder
import com.clicktive.domains.api.repository.ad.RawOrderRepository
import org.springframework.stereotype.Service

@Service
class GetOrderService(
    private val rawOrderRepository: RawOrderRepository
) {
    fun getRawOrder(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<RawOrder> {
        return rawOrderRepository.findAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}