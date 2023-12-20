package com.clicktive.domains.api.data.dao.company

import com.clicktive.domains.api.data.entity.member.Company
import com.clicktive.framework.springframework.base.BaseDao
import kr.placeup.framework.util.CommonUtils
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class CompanyDao(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) : BaseDao("CompanyDao"){

    private val rowMapper: RowMapper<Company> = BeanPropertyRowMapper.newInstance(Company::class.java)

    fun findAllCompanies(companyName: String?, companyStateCd: String?): MutableList<Company> {
        val params = MapSqlParameterSource()

        val queryBuilder = queryBuilder("findAllCompanies")

        if (CommonUtils.isNotNull(companyName)) {
            params.addValue("companyName", companyName)
            queryBuilder.append("and companyName like concat('%',:companyName,'%') ")
        }

        if (CommonUtils.isNotNull(companyStateCd)) {
            params.addValue("companyStateCd", companyStateCd)
            queryBuilder.append("and companyStateCd = :companyStateCd ")
        }

        return jdbcTemplate.query(
            queryBuilder.toString(),
            params,
            rowMapper
        )
    }
}
