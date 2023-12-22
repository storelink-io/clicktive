package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.repository.ad.SalesDailyRepository
import org.springframework.stereotype.Service

@Service
class GetSalesDailyService(
    private val salesDailyRepository: SalesDailyRepository
) {
}