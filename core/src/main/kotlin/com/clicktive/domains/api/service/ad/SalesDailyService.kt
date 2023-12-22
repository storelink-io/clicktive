package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.SalesDaily
import com.clicktive.domains.api.repository.ad.SalesDailyRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SalesDailyService(
    private val salesDailyRepository: SalesDailyRepository
) {
    @Transactional
    fun createSalesDaily(salesDaily: List<SalesDaily>): List<SalesDaily> {
        deleteSalesDaily(
            brandNo = salesDaily.first().brandNo,
            countryNo = salesDaily.first().countryNo,
            month = salesDaily.first().month
        )
        return salesDailyRepository.saveAll(salesDaily)
    }

    fun deleteSalesDaily(
        brandNo: Long,
        countryNo: Long,
        month: String
    ) {
        salesDailyRepository.deleteAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}