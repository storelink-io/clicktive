package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.dto.ad.DashboardRequest
import com.clicktive.domains.api.data.dto.ad.DashboardResponse
import com.clicktive.domains.api.data.entity.ad.AdDashboard
import com.clicktive.domains.api.repository.ad.AdDashboardRepository
import com.clicktive.framework.util.Mapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class DashboardService(
    private val adDashboardRepository: AdDashboardRepository
) {
    @Transactional
    fun saveDashboard(dashboardRequest: DashboardRequest): DashboardResponse {
        val dashboard = adDashboardRepository.findByBrandNoAndCountryNoAndMonth(
            brandNo = dashboardRequest.brandNo, countryNo = dashboardRequest.countryNo, month = dashboardRequest.month
        )

        val newDashboard = if (dashboard == null) {
            val adDashboard = AdDashboard(
                adDashboardNo = null,
                brandNo = dashboardRequest.brandNo,
                countryNo = dashboardRequest.countryNo,
                month = dashboardRequest.month,
                operationMemo = dashboardRequest.operationMemo,
                storeAdMemo = dashboardRequest.operationMemo,
                campaignMemo = dashboardRequest.campaignMemo,
                salesMemo = dashboardRequest.salesMemo,
                adKeywordMemo = dashboardRequest.adKeywordMemo
            )
            adDashboard.createMemberNo = 0

            adDashboardRepository.save(Mapper.convert(adDashboard))
        } else {
            dashboard.operationMemo = dashboardRequest.operationMemo
            dashboard.storeAdMemo = dashboardRequest.operationMemo
            dashboard.campaignMemo = dashboardRequest.campaignMemo
            dashboard.salesMemo = dashboardRequest.salesMemo
            dashboard.adKeywordMemo = dashboardRequest.adKeywordMemo
            dashboard.modifyDt = LocalDateTime.now()
            dashboard.modifyMemberNo = 0

            adDashboardRepository.save(dashboard)
        }

        return DashboardResponse(newDashboard)
    }
}