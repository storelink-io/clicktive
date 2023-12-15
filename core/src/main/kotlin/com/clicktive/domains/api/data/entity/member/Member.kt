package com.clicktive.domains.api.data.entity.member

import com.clicktive.domains.api.data.entity.BaseEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "ctv_mb_member")
@NoArg
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원 번호")
    var memberNo: Long?,

    @Comment("회원ID")
    var memberId: String?,

    @Comment("회원 명")
    var memberName: String?,

    @Comment("회사 번호")
    var companyNo: Long,

    @Comment("가입 일자")
    var registerDate: String,

    @Comment("회원 유형 코드")
    var memberTypeCd: String,

    @Comment("회원 상태 코드")
    var memberStateCd: String,

    @Comment("암호")
    var memberPw: String?,

    @Comment("직책")
    var jobPosition: String?,

    @Comment("휴대폰")
    var mobile: String?,

) : BaseEntity() {
    @Comment("세금계산서 수신 이메일")
    var taxEmail: String? = null

    @Comment("최근 로그인 일시")
    var lastLoginDt: LocalDateTime? = null

    @Comment("탈퇴 일자")
    var withdrawDate: String? = null

    @Comment("탈퇴 사유 코드")
    var withdrawReasonCd: String? = null

    @Comment("휴면 일자")
    var dormantDate: String? = null
}