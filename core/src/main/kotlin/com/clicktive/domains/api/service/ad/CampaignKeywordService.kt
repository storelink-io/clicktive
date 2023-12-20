package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.RawCampaignKeyword
import com.clicktive.domains.api.data.entity.ad.RawCampaignKeywordOrg
import com.clicktive.domains.api.repository.ad.RawCampaignKeywordOrgRepository
import com.clicktive.domains.api.repository.ad.RawCampaignKeywordRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CampaignKeywordService(
    private val rawCampaignKeywordOrgRepository: RawCampaignKeywordOrgRepository,
    private val rawCampaignKeywordRepository: RawCampaignKeywordRepository
) {
    @Transactional
    fun createRawCampaignKeywordOrg(rawCampaignKeywordOrg: List<RawCampaignKeywordOrg>): List<RawCampaignKeywordOrg> {
        deleteRawCampaignKeywordOrg(
            brandNo = rawCampaignKeywordOrg.first().brandNo,
            countryNo = rawCampaignKeywordOrg.first().countryNo,
            month = rawCampaignKeywordOrg.first().month
        )
        return rawCampaignKeywordOrgRepository.saveAll(rawCampaignKeywordOrg)
    }

    @Transactional
    fun createRawCampaignKeyword(rawCampaignKeyword: List<RawCampaignKeyword>): List<RawCampaignKeyword> {
        deleteRawCampaignKeyword(
            brandNo = rawCampaignKeyword.first().brandNo,
            countryNo = rawCampaignKeyword.first().countryNo,
            month = rawCampaignKeyword.first().month
        )
        return rawCampaignKeywordRepository.saveAll(rawCampaignKeyword)
    }

    fun deleteRawCampaignKeywordOrg(
        brandNo: Long,
        countryNo: Long,
        month: String
    ) {
        rawCampaignKeywordOrgRepository.deleteAllByBrandNoAndCountryNoAndMonth(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month
        )
    }

    fun deleteRawCampaignKeyword(
        brandNo: Int,
        countryNo: Int,
        month: String
    ) {
        rawCampaignKeywordRepository.deleteAllByBrandNoAndCountryNoAndMonth(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month
        )
    }
}