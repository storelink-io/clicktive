package com.clicktive.domains.api.service.member

import com.clicktive.domains.api.config.security.JwtToken
import com.clicktive.domains.api.config.security.JwtTokenProvider
import com.clicktive.domains.api.data.dto.member.TokenResponse
import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.domains.api.data.entity.member.MemberToken
import com.clicktive.domains.api.repository.member.MemberRepository
import com.clicktive.domains.api.repository.member.MemberTokenRepository
import com.clicktive.framework.exception.ServiceException
import com.clicktive.framework.util.Mapper
import jakarta.transaction.Transactional
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class Oauth2Service (
    val memberTokenRepository: MemberTokenRepository,
    val memberBasicRepository: MemberRepository,
    val jwtTokenProvider: JwtTokenProvider
) {
    @Transactional
    fun generateToken(member: Member): TokenResponse {
        var accessToken: JwtToken? = null
        var refreshToken: JwtToken? = null

        runBlocking {
            // 토큰
            val accessTokenTask = async {
                jwtTokenProvider.createAccessToken(
                    member,
                    arrayListOf("ADMIN")
                )
            }

            // 리플레시 토큰
            val refreshTokenTask = async {
                jwtTokenProvider.createRefreshToken(member.memberNo!!)
            }

            accessToken = accessTokenTask.await()
            refreshToken = refreshTokenTask.await()

            // 회원 토큰 생성함
            val memberToken = MemberToken(
                accessToken!!.token,
                "login",
                member.memberNo,
                email = null,
                tokenDueDt = accessToken!!.dueDt,
                refreshToken = refreshToken!!.token,
                refreshTokenDueDt = refreshToken!!.dueDt,
                createDt = LocalDateTime.now()
            )
            memberTokenRepository.save(memberToken)
        }

        return TokenResponse(
            accessToken = accessToken!!.token,
            refreshToken = refreshToken!!.token,
            tokenDueDt = accessToken!!.dueDt,
            refreshTokenDueDt = refreshToken!!.dueDt
        )
    }

    @Transactional
    fun refreshToken(token: String, refreshToken: String): TokenResponse {

        // 회원 토큰 정보 조회 : 리플레시 토큰
        val tempToken = token.substring("Bearer ".length)
        val memberToken = memberTokenRepository.getByTokenAndRefreshToken(tempToken, refreshToken) ?: throw ServiceException("AUTH-001")
        val unvalidated = memberToken.refreshTokenDueDt!!.before(Date()) //true이면 유효기간이 말료됨
        if (unvalidated) {
            throw ServiceException("AUTH-001")
        }

        // 회원 정보 조회
        val member = memberBasicRepository.getByMemberNo(memberToken.memberNo)!! ?: throw ServiceException("MEM-001")

        val accessToken = jwtTokenProvider.createAccessToken(
            member,
            arrayListOf(Mapper.convert("ADMIN"))
        )

        // 회원 토큰 생성함
        val newMemberToken = MemberToken(
            accessToken.token,
            "login",
            member.memberNo,
            email = null,
            tokenDueDt = accessToken.dueDt,
            refreshToken = memberToken.refreshToken,
            refreshTokenDueDt = memberToken.refreshTokenDueDt,
            createDt = LocalDateTime.now()
        )
        memberTokenRepository.save(newMemberToken)

        return TokenResponse(
            accessToken = accessToken.token,
            tokenDueDt = accessToken.dueDt,
            refreshToken = memberToken.refreshToken!!,
            refreshTokenDueDt = memberToken.refreshTokenDueDt!!
        )
    }
}