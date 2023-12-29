package com.clicktive.domains.api.data.dao.ad

import com.clicktive.domains.api.data.dto.ad.RawCampaignKeywordRequest
import com.clicktive.domains.api.data.entity.ad.RawCampaignKeyword
import com.clicktive.framework.springframework.base.BaseDao
import kr.placeup.framework.util.CommonUtils.Companion.isNotNull
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class RawCampaignKeywordDao(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : BaseDao("RawCampaignKeywordDao") {
    private val rowMapper: RowMapper<RawCampaignKeyword> =
        BeanPropertyRowMapper.newInstance(RawCampaignKeyword::class.java)

    fun getRawCampaignKeyword(
        brandNo: Long,
        countryNo: Long,
        month: String,
        rawCampaignKeywordRequest: RawCampaignKeywordRequest
    ): MutableList<RawCampaignKeyword> {
        val queryBuilder = queryBuilder("getAllRawCampaignKeyword")
        val params = MapSqlParameterSource()

        params.addValue("brandNo", brandNo)
        queryBuilder.append("and brandNo = :brandNo ")

        params.addValue("countryNo", countryNo)
        queryBuilder.append("and countryNo = :countryNo ")

        params.addValue("month", month)
        queryBuilder.append("and month = :month ")

        if (isNotNull(rawCampaignKeywordRequest.keyword)) {
            params.addValue("keyword", rawCampaignKeywordRequest.keyword)
            queryBuilder.append("and keyword like concat('%',:keyword,'%') ")
        }

        if (isNotNull(rawCampaignKeywordRequest.minExpenseAmt)) {
            params.addValue("minExpenseAmt", rawCampaignKeywordRequest.minExpenseAmt)
            queryBuilder.append("and :minExpenseAmt <= expenseAmt ")
        }

        if (isNotNull(rawCampaignKeywordRequest.maxExpenseAmt)) {
            params.addValue("maxExpenseAmt", rawCampaignKeywordRequest.maxExpenseAmt)
            queryBuilder.append("and expenseAmt >= :maxExpenseAmt ")
        }

        if (isNotNull(rawCampaignKeywordRequest.minSalesTotal7Amt)) {
            params.addValue("minSalesTotal7Amt", rawCampaignKeywordRequest.minSalesTotal7Amt)
            queryBuilder.append("and :minSalesTotal7Amt <= salesTotal7Amt ")
        }

        if (isNotNull(rawCampaignKeywordRequest.maxSalesTotal7Amt)) {
            params.addValue("maxSalesTotal7Amt", rawCampaignKeywordRequest.maxSalesTotal7Amt)
            queryBuilder.append("and salesTotal7Amt >= :maxSalesTotal7Amt ")
        }

        if (isNotNull(rawCampaignKeywordRequest.minRoas)) {
            params.addValue("minRoas", rawCampaignKeywordRequest.minRoas)
            queryBuilder.append("and :minRoas <= roas ")
        }

        if (isNotNull(rawCampaignKeywordRequest.maxRoas)) {
            params.addValue("maxRoas", rawCampaignKeywordRequest.maxRoas)
            queryBuilder.append("and roas >= :maxRoas ")
        }

        if (isNotNull(rawCampaignKeywordRequest.minAcos)) {
            params.addValue("minAcos", rawCampaignKeywordRequest.minAcos)
            queryBuilder.append("and :minAcos <= acos ")
        }

        if (isNotNull(rawCampaignKeywordRequest.maxAcos)) {
            params.addValue("maxAcos", rawCampaignKeywordRequest.maxAcos)
            queryBuilder.append("and acos >= :maxAcos ")
        }

        if (isNotNull(rawCampaignKeywordRequest.minViewNum)) {
            params.addValue("minViewNum", rawCampaignKeywordRequest.minViewNum)
            queryBuilder.append("and :minViewNum <= viewNum ")
        }

        if (isNotNull(rawCampaignKeywordRequest.maxViewNum)) {
            params.addValue("maxViewNum", rawCampaignKeywordRequest.maxViewNum)
            queryBuilder.append("and viewNum >= :maxViewNum ")
        }

        if (isNotNull(rawCampaignKeywordRequest.minClickNum)) {
            params.addValue("minClickNum", rawCampaignKeywordRequest.minClickNum)
            queryBuilder.append("and :minClickNum <= clickNum ")
        }

        if (isNotNull(rawCampaignKeywordRequest.maxClickNum)) {
            params.addValue("maxClickNum", rawCampaignKeywordRequest.maxClickNum)
            queryBuilder.append("and clickNum >= :maxClickNum ")
        }

        if (isNotNull(rawCampaignKeywordRequest.minCtr)) {
            params.addValue("minCtr", rawCampaignKeywordRequest.minCtr)
            queryBuilder.append("and :minCtr <= ctr ")
        }

        if (isNotNull(rawCampaignKeywordRequest.maxCtr)) {
            params.addValue("maxCtr", rawCampaignKeywordRequest.maxCtr)
            queryBuilder.append("and ctr >= :maxCtr ")
        }

        if (isNotNull(rawCampaignKeywordRequest.minCpc)) {
            params.addValue("minCpc", rawCampaignKeywordRequest.minCpc)
            queryBuilder.append("and :minCpc <= cpc ")
        }

        if (isNotNull(rawCampaignKeywordRequest.maxCpc)) {
            params.addValue("maxCpc", rawCampaignKeywordRequest.maxCpc)
            queryBuilder.append("and cpc >= :maxCpc ")
        }

        return jdbcTemplate.query(
            queryBuilder.toString(),
            params,
            rowMapper
        )
    }

    fun getRawCampaignKeywordMonthly(
        brandNo: Long,
        countryNo: Long,
        keyword: String
    ): MutableList<RawCampaignKeyword> {
        val queryBuilder = queryBuilder("getAllRawCampaignKeyword")
        val params = MapSqlParameterSource()

        params.addValue("brandNo", brandNo)
        queryBuilder.append("and brandNo = :brandNo ")

        params.addValue("countryNo", countryNo)
        queryBuilder.append("and countryNo = :countryNo ")

        params.addValue("keyword", keyword)
        queryBuilder.append("and keyword = :keyword ")

        queryBuilder.append("order by month")

        return jdbcTemplate.query(
            queryBuilder.toString(),
            params,
            rowMapper
        )
    }
}