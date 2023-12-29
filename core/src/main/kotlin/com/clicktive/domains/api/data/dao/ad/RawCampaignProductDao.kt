package com.clicktive.domains.api.data.dao.ad

import com.clicktive.domains.api.data.dto.ad.RawCampaignProductRequest
import com.clicktive.domains.api.data.entity.ad.RawCampaignProduct
import com.clicktive.framework.springframework.base.BaseDao
import kr.placeup.framework.util.CommonUtils.Companion.isNotNull
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class RawCampaignProductDao(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : BaseDao("RawCampaignProductDao") {
    private val rowMapper: RowMapper<RawCampaignProduct> =
        BeanPropertyRowMapper.newInstance(RawCampaignProduct::class.java)

    fun getRawCampaignProduct(
        brandNo: Long,
        countryNo: Long,
        month: String,
        rawCampaignProductRequest: RawCampaignProductRequest
    ): MutableList<RawCampaignProduct> {
        val queryBuilder = queryBuilder("getAllCalculateRawCampaignProduct")
        val params = MapSqlParameterSource()

        params.addValue("brandNo", brandNo)
        queryBuilder.append("and brandNo = :brandNo ")

        params.addValue("countryNo", countryNo)
        queryBuilder.append("and countryNo = :countryNo ")

        params.addValue("month", month)
        queryBuilder.append("and month = :month ")

        if (isNotNull(rawCampaignProductRequest.asin)) {
            params.addValue("asin", rawCampaignProductRequest.asin)
            queryBuilder.append("and asin = :asin ")
        } else {
            if (isNotNull(rawCampaignProductRequest.minExpenseAmt)) {
                params.addValue("minExpenseAmt", rawCampaignProductRequest.minExpenseAmt)
                queryBuilder.append("and :minExpenseAmt <= expenseAmt ")
            }

            if (isNotNull(rawCampaignProductRequest.maxExpenseAmt)) {
                params.addValue("maxExpenseAmt", rawCampaignProductRequest.maxExpenseAmt)
                queryBuilder.append("and expenseAmt >= :maxExpenseAmt ")
            }

            if (isNotNull(rawCampaignProductRequest.minSalesTotal7Amt)) {
                params.addValue("minSalesTotal7Amt", rawCampaignProductRequest.minSalesTotal7Amt)
                queryBuilder.append("and :minSalesTotal7Amt <= salesTotal7Amt ")
            }

            if (isNotNull(rawCampaignProductRequest.maxSalesTotal7Amt)) {
                params.addValue("maxSalesTotal7Amt", rawCampaignProductRequest.maxSalesTotal7Amt)
                queryBuilder.append("and salesTotal7Amt >= :maxSalesTotal7Amt ")
            }

            if (isNotNull(rawCampaignProductRequest.minRoas)) {
                params.addValue("minRoas", rawCampaignProductRequest.minRoas)
                queryBuilder.append("and :minRoas <= roas ")
            }

            if (isNotNull(rawCampaignProductRequest.maxRoas)) {
                params.addValue("maxRoas", rawCampaignProductRequest.maxRoas)
                queryBuilder.append("and roas >= :maxRoas ")
            }

            if (isNotNull(rawCampaignProductRequest.minAcos)) {
                params.addValue("minAcos", rawCampaignProductRequest.minAcos)
                queryBuilder.append("and :minAcos <= acos ")
            }

            if (isNotNull(rawCampaignProductRequest.maxAcos)) {
                params.addValue("maxAcos", rawCampaignProductRequest.maxAcos)
                queryBuilder.append("and acos >= :maxAcos ")
            }

            if (isNotNull(rawCampaignProductRequest.minViewNum)) {
                params.addValue("minViewNum", rawCampaignProductRequest.minViewNum)
                queryBuilder.append("and :minViewNum <= viewNum ")
            }

            if (isNotNull(rawCampaignProductRequest.maxViewNum)) {
                params.addValue("maxViewNum", rawCampaignProductRequest.maxViewNum)
                queryBuilder.append("and viewNum >= :maxViewNum ")
            }

            if (isNotNull(rawCampaignProductRequest.minClickNum)) {
                params.addValue("minClickNum", rawCampaignProductRequest.minClickNum)
                queryBuilder.append("and :minClickNum <= clickNum ")
            }

            if (isNotNull(rawCampaignProductRequest.maxClickNum)) {
                params.addValue("maxClickNum", rawCampaignProductRequest.maxClickNum)
                queryBuilder.append("and clickNum >= :maxClickNum ")
            }

            if (isNotNull(rawCampaignProductRequest.minCtr)) {
                params.addValue("minCtr", rawCampaignProductRequest.minCtr)
                queryBuilder.append("and :minCtr <= ctr ")
            }

            if (isNotNull(rawCampaignProductRequest.maxCtr)) {
                params.addValue("maxCtr", rawCampaignProductRequest.maxCtr)
                queryBuilder.append("and ctr >= :maxCtr ")
            }

            if (isNotNull(rawCampaignProductRequest.minCpc)) {
                params.addValue("minCpc", rawCampaignProductRequest.minCpc)
                queryBuilder.append("and :minCpc <= cpc ")
            }

            if (isNotNull(rawCampaignProductRequest.maxCpc)) {
                params.addValue("maxCpc", rawCampaignProductRequest.maxCpc)
                queryBuilder.append("and cpc >= :maxCpc ")
            }
        }

        return jdbcTemplate.query(
            queryBuilder.toString(),
            params,
            rowMapper
        )
    }

    fun getRawCampaignProductMonthly(
        brandNo: Long,
        countryNo: Long,
        asin: String
    ): MutableList<RawCampaignProduct> {
        val queryBuilder = queryBuilder("getAllCalculateRawCampaignProduct")
        val params = MapSqlParameterSource()

        params.addValue("brandNo", brandNo)
        queryBuilder.append("and brandNo = :brandNo ")

        params.addValue("countryNo", countryNo)
        queryBuilder.append("and countryNo = :countryNo ")

        params.addValue("asin", asin)
        queryBuilder.append("and asin = :asin ")

        queryBuilder.append("order by month")

        return jdbcTemplate.query(
            queryBuilder.toString(),
            params,
            rowMapper
        )
    }
}