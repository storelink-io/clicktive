package com.clicktive.domains.api.data.dao.brand

import com.clicktive.domains.api.data.entity.member.Brand
import com.clicktive.framework.springframework.base.BaseDao
import kr.placeup.framework.util.CommonUtils
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class BrandDao(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) : BaseDao("BrandDao"){

    private val rowMapper: RowMapper<Brand> = BeanPropertyRowMapper.newInstance(Brand::class.java)

    fun getAllBrands(brandName: String?, brandStateCd: String?): MutableList<Brand> {
        val params = MapSqlParameterSource()

        val queryBuilder = queryBuilder("findAllBrands")

        if (CommonUtils.isNotNull(brandName)) {
            params.addValue("brandName", brandName)
            queryBuilder.append("and brandName like concat('%',:brandName,'%') ")
        }

        if (CommonUtils.isNotNull(brandStateCd)) {
            params.addValue("brandStateCd", brandStateCd)
            queryBuilder.append("and brandStateCd = :brandStateCd ")
        }

        return jdbcTemplate.query(
            queryBuilder.toString(),
            params,
            rowMapper
        )
    }

    fun findMemberBrands(memberNo: Long): MutableList<Brand> {
        val params = MapSqlParameterSource("memberNo", memberNo)

        val queryBuilder = queryBuilder("findMemberBrands")

        return jdbcTemplate.query(
            queryBuilder.toString(),
            params,
            rowMapper
        )
    }

    fun findCountryBrands(countryNo: Long): MutableList<Brand> {
        val params = MapSqlParameterSource("countryNo", countryNo)

        val queryBuilder = queryBuilder("findCountryBrands")

        return jdbcTemplate.query(
            queryBuilder.toString(),
            params,
            rowMapper
        )
    }
}
