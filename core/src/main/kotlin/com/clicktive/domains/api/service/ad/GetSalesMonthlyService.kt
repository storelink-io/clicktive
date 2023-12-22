package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.repository.ad.SalesMonthlyRepository
import org.springframework.stereotype.Service

@Service
class GetSalesMonthlyService(
    private val salesMonthlyRepository: SalesMonthlyRepository
) {
}