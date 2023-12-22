package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.dto.ad.DashboardResponse
import com.clicktive.domains.api.repository.ad.AdDashboardRepository
import org.springframework.stereotype.Service

@Service
class GetDashboardService(
    private val adDashboardRepository: AdDashboardRepository
) {
    fun getDashboardResponse(
        brandNo: Long,
        countryNo: Long,
        month: String,
    ): DashboardResponse {
        val dashboard = adDashboardRepository.findByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
        return dashboard.takeIf { it != null }?.let {
            DashboardResponse(it)
        } ?: DashboardResponse(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}