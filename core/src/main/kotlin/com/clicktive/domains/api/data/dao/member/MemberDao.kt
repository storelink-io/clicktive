package com.clicktive.domains.api.data.dao.member

import com.clicktive.domains.api.data.dto.member.MemberRequest
import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.framework.springframework.base.BaseDao
import kr.placeup.framework.util.CommonUtils
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class MemberDao(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) : BaseDao("MemberDao"){

    private val rowMapper: RowMapper<Member> = BeanPropertyRowMapper.newInstance(Member::class.java)

    fun findAllMembers(memberRequest: MemberRequest): MutableList<Member> {
        val params = MapSqlParameterSource()

        val queryBuilder = queryBuilder("findAllMembers")

        if (CommonUtils.isNotNull(memberRequest.memberName)) {
            params.addValue("memberName", memberRequest.memberName)
            queryBuilder.append("and memberName like concat('%',:memberName,'%') ")
        }

        if (CommonUtils.isNotNull(memberRequest.memberStateCd?.code)) {
            params.addValue("memberStateCd", memberRequest.memberStateCd?.code)
            queryBuilder.append("and memberStateCd = :memberStateCd ")
        }

        return jdbcTemplate.query(
            queryBuilder.toString(),
            params,
            rowMapper
        )
    }
}
