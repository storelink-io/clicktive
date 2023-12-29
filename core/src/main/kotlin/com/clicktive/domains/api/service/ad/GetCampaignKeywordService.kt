package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.dao.ad.RawCampaignKeywordDao
import com.clicktive.domains.api.data.dto.ad.CampaignKeywordTop10Response
import com.clicktive.domains.api.data.dto.ad.RawCampaignKeywordMonthlyResponse
import com.clicktive.domains.api.data.dto.ad.RawCampaignKeywordRequest
import com.clicktive.domains.api.data.dto.ad.RawCampaignKeywordResponse
import com.clicktive.domains.api.data.entity.ad.RawCampaignKeyword
import com.clicktive.domains.api.repository.ad.RawCampaignKeywordRepository
import org.springframework.stereotype.Service

@Service
class GetCampaignKeywordService(
    private val rawCampaignKeywordRepository: RawCampaignKeywordRepository,
    private val rawCampaignKeywordDao: RawCampaignKeywordDao
) {
    fun getRawCampaignKeyword(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<RawCampaignKeyword> {
        return rawCampaignKeywordRepository.findAllByBrandNoAndCountryNoAndMonth(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month
        )
    }

    // TODO 키워드 광고 TOP 10 구분 내용 이해 X 일단 그냥 키워드를 기준으로 group by 함
    fun getCampaignKeywordTop10Response(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<CampaignKeywordTop10Response> {
        val rawCampaignKeyword = getRawCampaignKeyword(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month
        )
        return rawCampaignKeyword.sortedByDescending { it.expenseAmt }.take(10).mapIndexed { index, it ->
            CampaignKeywordTop10Response(rank = (index + 1), rawCampaignKeyword = it)
        }
    }

    fun getRawCampaignKeywordResponse(
        brandNo: Long,
        countryNo: Long,
        month: String,
        rawCampaignKeywordRequest: RawCampaignKeywordRequest
    ): List<RawCampaignKeywordResponse> {
        return rawCampaignKeywordDao.getRawCampaignKeyword(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            rawCampaignKeywordRequest = rawCampaignKeywordRequest
        ).map {
            RawCampaignKeywordResponse(it)
        }
    }

    fun getRawCampaignKeywordMonthlyResponse(
        brandNo: Long,
        countryNo: Long,
        keyword: String
    ): List<RawCampaignKeywordMonthlyResponse> {
        return rawCampaignKeywordDao.getRawCampaignKeywordMonthly(
            brandNo = brandNo,
            countryNo = countryNo,
            keyword = keyword
        ).map {
            RawCampaignKeywordMonthlyResponse(it)
        }
    }
}