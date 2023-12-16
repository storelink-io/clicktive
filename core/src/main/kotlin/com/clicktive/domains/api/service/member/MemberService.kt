package com.clicktive.domains.api.service.member

import com.clicktive.domains.api.data.dto.member.MemberRegisterRequest
import com.clicktive.domains.api.data.entity.member.Member
import com.clicktive.domains.api.data.enum.member.MemberStateEnum
import com.clicktive.domains.api.repository.member.MemberRepository
import com.clicktive.framework.exception.ServiceException
import com.clicktive.framework.util.Mapper
import jakarta.transaction.Transactional
import kr.placeup.framework.util.CommonUtils
import com.clicktive.framework.util.DateUtils
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MemberService (
    private val passwordEncoder: BCryptPasswordEncoder,
    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun createAndUpdateMember(request: MemberRegisterRequest) {
        if (CommonUtils.isNull(request.memberName)) {
            throw ServiceException("COMM-004", messageParams = arrayOf("회원 이름"))
        }

        if (CommonUtils.isNull(request.memberId)) {
            throw ServiceException("COMM-004", messageParams = arrayOf("회원 아이디(이메일)"))
        }

//        if (CommonUtils.isNull(request.memberPw) || CommonUtils.isNull(request.confirmPw)) {
//            throw ServiceException("COMM-004", messageParams = arrayOf("비밀 번호"))
//        }

        if (CommonUtils.isNotNull(request.memberPw) && CommonUtils.isNotNull(request.confirmPw)) {
            // 비밀 번호 유효성 체크
            if (request.memberPw != request.confirmPw) {
                throw ServiceException("MEM-003")
            }

            if (request.confirmPw.length < 6) {
                throw ServiceException("MEM-007")
            }
        }

        // 휴대폰 번호 체크 추가
//        if (memberRepository.findByMobile(request.mobile!!).isNotEmpty()) {
//            throw ServiceException("MEM-012")
//        }

        lateinit var member: Member
        if (request.memberNo == null) {
            // 회원 ID로 회원 정보 체크
            if (memberRepository.getByMemberId(request.memberId) != null) {
                throw ServiceException("MEM-002")
            }

            member = Mapper.convert(request)
            memberRepository.save(member)

            member.registerDate  = DateUtils.currentDate()
            member.memberPw      = passwordEncoder.encode(request.confirmPw)

            member.modifyDt      = member.createDt
            member.createMemberNo = member.memberNo //TODO 로그인한 사람으로 나중에 변경해야함.
            member.modifyMemberNo = member.memberNo

        } else {
            member = memberRepository.getReferenceById(request!!.memberNo!!)

            //비밀번호와 비밀번호 확인 있는 경우만 비밀번호 변경함
            if (CommonUtils.isNotNull(request.memberPw) && CommonUtils.isNotNull(request.confirmPw)) {
                member.memberPw   = passwordEncoder.encode(request.confirmPw)
            }

            member.companyNo      = request.companyNo
            member.memberName     = request.memberName
            member.mobile         = request.mobile
            member.modifyDt       = LocalDateTime.now()
            member.modifyMemberNo = member.memberNo //TODO 로그인한 사람으로 나중에 변경해야함.
        }
        member.memberStateCd  = request.memberStateCd.code
        member.memberTypeCd   = request.memberTypeCd.code
        memberRepository.save(member)
    }
}