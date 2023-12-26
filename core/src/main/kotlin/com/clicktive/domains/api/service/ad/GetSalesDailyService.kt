package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.SalesDaily
import com.clicktive.domains.api.repository.ad.SalesDailyRepository
import org.springframework.stereotype.Service

@Service
class GetSalesDailyService(
    private val salesDailyRepository: SalesDailyRepository
) {
    fun getSalesDaily(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<SalesDaily> {
        return salesDailyRepository.findAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}