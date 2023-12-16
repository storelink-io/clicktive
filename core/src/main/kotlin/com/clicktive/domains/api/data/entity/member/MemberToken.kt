package com.clicktive.domains.api.data.entity.member

import com.clicktive.domains.api.data.entity.BaseEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "ctv_mb_member_token")
@NoArg
class MemberToken (
    @Id
    @org.springframework.data.annotation.Id
    @Comment("토큰")
    var token: String?,

    @Comment("토큰유형코드")
    var tokenTypeCd: String?,

    @Comment("회원 번호")
    var memberNo: Long?,

    @Comment("이메일")
    var email: String?,

    @Comment("토큰 만기일시")
    var tokenDueDt: Date?,

    @Comment("Refresh 토큰")
    var refreshToken: String?,

    @Comment("Refresh 만기일시")
    var refreshTokenDueDt: Date?,

    @Comment("생성 일시")
    var createDt: LocalDateTime
) {
}