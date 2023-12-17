package com.clicktive.domains.api.process.member

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import com.clicktive.domains.api.data.dto.member.TokenResponse
import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.domains.api.data.enum.member.MemberStateEnum
import com.clicktive.domains.api.repository.member.MemberRepository
import com.clicktive.domains.api.data.dto.member.MemberResponseDto
import com.clicktive.domains.api.service.member.Oauth2Service
import com.clicktive.framework.exception.ServiceException
import com.clicktive.framework.util.Mapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class SignInProcess() {
    @Autowired
    private lateinit var oauth2Service: Oauth2Service

    @Autowired
    private lateinit var memberBasicRepository: MemberRepository

    fun doProcess(
        memberId: String,
        memberPw: String,
    ): MemberResponseDto {
        var loginMember: Member? = null
        var loginToken: TokenResponse? = null

        runBlocking {
            //회원 정보 조회 : memberId
            val loginMemberTask = async {
                memberBasicRepository.getByMemberId(memberId)
            }
            loginMember = loginMemberTask.await()

            if (loginMember == null) {
                throw ServiceException("AUTH-002")
            } else {
                if (loginMember!!.memberStateCd == MemberStateEnum.정지.code
                    || loginMember!!.memberStateCd == MemberStateEnum.탈퇴.code
                ) {
                    throw ServiceException("AUTH-002")
                }

                if (loginMember!!.memberStateCd == MemberStateEnum.휴면.code
                ) {
                    throw ServiceException("AUTH-007")
                }

                if (!BCrypt.checkpw(memberPw, loginMember!!.memberPw)) {
                    throw ServiceException("AUTH-003")
                }
            }

            val loginTokenTask = async {
                oauth2Service.generateToken(loginMember!!)
            }

            loginToken = loginTokenTask.await()

            launch {
                loginMember!!.lastLoginDt = LocalDateTime.now()
                loginMember!!.modifyDt = LocalDateTime.now()
                loginMember!!.modifyMemberNo = loginMember!!.memberNo
                memberBasicRepository.save(loginMember!!)
            }
        }

        val memberResponse: MemberResponseDto = Mapper.convert(loginMember!!)
        memberResponse.accessToken = loginToken!!.accessToken
        memberResponse.refreshToken = loginToken!!.refreshToken
        memberResponse.tokenDueDt = loginToken!!.tokenDueDt
        memberResponse.refreshTokenDueDt = loginToken!!.refreshTokenDueDt

        return memberResponse
    }
}