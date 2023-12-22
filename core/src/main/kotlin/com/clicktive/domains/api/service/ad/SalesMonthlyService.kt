package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.SalesMonthly
import com.clicktive.domains.api.repository.ad.SalesMonthlyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SalesMonthlyService(
    private val salesMonthlyRepository: SalesMonthlyRepository
) {
    @Transactional
    fun createSalesMonthly(salesMonthly: SalesMonthly): SalesMonthly {
        deleteSalesMonthly(
            brandNo = salesMonthly.brandNo,
            countryNo = salesMonthly.countryNo,
            month = salesMonthly.month
        )
        return salesMonthlyRepository.save(salesMonthly)
    }

    fun deleteSalesMonthly(
        brandNo: Long,
        countryNo: Long,
        month: String
    ) {
        salesMonthlyRepository.deleteByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}