package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.dto.ad.CampaignKeywordTop10Response
import com.clicktive.domains.api.data.entity.ad.RawCampaignKeyword
import com.clicktive.domains.api.repository.ad.RawCampaignKeywordRepository
import org.springframework.stereotype.Service

@Service
class GetCampaignKeywordService(
    private val rawCampaignKeywordRepository: RawCampaignKeywordRepository
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
}