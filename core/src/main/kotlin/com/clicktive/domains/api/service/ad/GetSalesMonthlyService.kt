package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.dto.ad.SaleMonthlyResponse
import com.clicktive.domains.api.data.entity.ad.SalesMonthly
import com.clicktive.domains.api.repository.ad.SalesMonthlyRepository
import com.clicktive.framework.exception.ServiceException
import org.springframework.stereotype.Service

@Service
class GetSalesMonthlyService(
    private val salesMonthlyRepository: SalesMonthlyRepository
) {
    fun getSalesMonthly(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): SalesMonthly? {
        return salesMonthlyRepository.findByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }

    fun getSaleMonthlyResponse(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): SaleMonthlyResponse {
        val salesMonthly = getSalesMonthly(brandNo = brandNo, countryNo = countryNo, month = month) ?: throw ServiceException("")
        return SaleMonthlyResponse(salesMonthly)
    }
}