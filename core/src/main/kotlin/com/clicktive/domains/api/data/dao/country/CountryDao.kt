package com.clicktive.domains.api.data.dao.country

import com.clicktive.domains.api.data.entity.member.Country
import com.clicktive.framework.springframework.base.BaseDao
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class CountryDao(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) : BaseDao("CountryDao"){

    private val rowMapper: RowMapper<Country> = BeanPropertyRowMapper.newInstance(Country::class.java)

    fun findBrandCountries(brandNo: Long): MutableList<Country> {
        val params = MapSqlParameterSource("brandNo", brandNo)

        val queryBuilder = queryBuilder("findBrandCountries")

        return jdbcTemplate.query(
            queryBuilder.toString(),
            params,
            rowMapper
        )
    }
}
