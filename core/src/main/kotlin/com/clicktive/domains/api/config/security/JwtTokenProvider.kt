package com.clicktive.domains.api.config.security

import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.domains.api.repository.member.MemberTokenRepository
import com.clicktive.domains.api.service.AuthenticationService
import com.clicktive.framework.exception.ServiceException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec


@Component
class JwtTokenProvider(
    val authenticationService: AuthenticationService,
    val memberTokenRepository: MemberTokenRepository,
) {
    val accessTokenValidMillisecond = 1000L * 60 * 60 * 24 * 14
    val refreshTokenValidMillisecond = 1000L * 60 * 60 * 24 * 30

    @Value("\${spring.jwt.secret}")
    var jwtSecret: String = ""

    @PostConstruct
    protected fun init() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.toByteArray(StandardCharsets.UTF_8))
    }

    fun authenticate(token: String): Authentication {
        val memberToken = memberTokenRepository.getValidToken(token) ?: throw ServiceException("AUTH-001")
        val accountAdapter = authenticationService.loadUserByUsername(getId(token))
        return UsernamePasswordAuthenticationToken(accountAdapter, "", accountAdapter.authorities)
    }

    fun createAccessToken(member: Member, roles: List<String>): JwtToken {
        val key: Key = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
        val now = Date()
        val expiredDt = Date(now.time + accessTokenValidMillisecond)
        val token = Jwts.builder()
            .issuer("clicktive.com")
            .subject(member.memberNo.toString())
            .claim("memberNo", member.memberNo)
            .claim("memberId", member.memberId)
            .claim("memberTypeCd", member.memberTypeCd)
            .claim("memberName", member.memberName)
            .expiration(expiredDt)
            .signWith(key)
            .compact()
        return JwtToken(
            token,
            expiredDt
        )
    }

    fun createRefreshToken(id: Long): JwtToken {
        //val key = Jwts.SIG.HS256.key().build()
        val key = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
        val now = Date()
        val expiredDt = Date(now.time + refreshTokenValidMillisecond)
        val token = Jwts.builder()
            .issuer("clicktive.com")
            .subject(id.toString())
            .expiration(expiredDt)
            .signWith(key)
            .compact()

        return JwtToken(
            token,
            expiredDt
        )
    }

    fun getId(token: String): String {
        val key = Keys.hmacShaKeyFor(jwtSecret.toByteArray())
        return Jwts
            .parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
            .subject
    }

    fun resolveToken(request: HttpServletRequest): String? = request.getHeader("Authorization")

    fun validateToken(token: String): Boolean = try {
        val memberToken = memberTokenRepository.getByToken(token)
        memberToken.tokenDueDt!!.after(Date()) // 토큰 유효기간 > 현재 이면 true(유효) 아니면 false(말료)
    } catch (e: Exception) {
        false
    }

    fun validateRefreshToken(token: String, refreshToken: String): Boolean {
        var ret = true
        try {
            val accessToken = token.split("Bearer ")[1]
            val memberToken = memberTokenRepository.getByTokenAndRefreshToken(accessToken, refreshToken)!!
            ret = memberToken.refreshTokenDueDt!!.after(Date()) // 리플레시 유효기간 > 현재 이면 true(유효) 아니면 false(말료)
        } catch (e: Exception) {
            e.printStackTrace()
            ret = false
        }
        return ret
    }

    fun getAccessToken(request: HttpServletRequest): String? {
        val token = resolveToken(request)
        if (token != null) {
            return if (token.length > "Bearer ".length) token.substring("Bearer ".length) else null
        }
        return null
    }
}