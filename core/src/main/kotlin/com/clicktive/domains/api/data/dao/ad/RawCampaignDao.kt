package com.clicktive.domains.api.data.dao.ad

import com.clicktive.domains.api.data.entity.ad.RawCampaign
import com.clicktive.framework.springframework.base.BaseDao
import kr.placeup.framework.util.CommonUtils.Companion.isNotNull
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class RawCampaignDao(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : BaseDao("RawCampaignDao") {
    private val rowMapper: RowMapper<RawCampaign> = BeanPropertyRowMapper.newInstance(RawCampaign::class.java)

    fun getRawCampaign(
        brandNo: Long,
        countryNo: Long,
        month: String,
        campaignTypeCd: String,
        portfolioName: String,
        campaignName: String
    ): MutableList<RawCampaign> {
        val queryBuilder = queryBuilder("getAllRawCampaign")
        val params = MapSqlParameterSource()

        params.addValue("brandNo", brandNo)
        queryBuilder.append("and brandNo = :brandNo ")

        params.addValue("countryNo", countryNo)
        queryBuilder.append("and countryNo = :countryNo ")

        params.addValue("month", month)
        queryBuilder.append("and month = :month ")

        // TODO campaignTypeCd 어떻게 처리할 건지 그냥 like절 하기에는 좀..
        if (isNotNull(campaignTypeCd)) {
            params.addValue("campaignTypeCd", campaignTypeCd)
            queryBuilder.append("and campaignType like concat('%',:campaignTypeCd,'%') ")
        }

        if (isNotNull(portfolioName)) {
            params.addValue("portfolioName", portfolioName)
            queryBuilder.append("and portfolioName like concat('%',:portfolioName,'%') ")
        }

        if (isNotNull(campaignName)) {
            params.addValue("campaignName", campaignName)
            queryBuilder.append("and campaignName like concat('%',:campaignName,'%') ")
        }

        return jdbcTemplate.query(
            queryBuilder.toString(),
            params,
            rowMapper
        )
    }
}