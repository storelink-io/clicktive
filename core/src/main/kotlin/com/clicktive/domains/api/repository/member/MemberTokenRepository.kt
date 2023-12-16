package com.clicktive.domains.api.repository.member

import com.clicktive.domains.api.data.entity.member.MemberToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MemberTokenRepository: JpaRepository<MemberToken, String> {
    fun getByTokenAndRefreshToken(token: String, refreshToken: String): MemberToken?

    fun getByToken(token: String): MemberToken

    @Query("select main from MemberToken main where main.token = :token and main.tokenDueDt > now() ")
    fun getValidToken(token: String): MemberToken?
}