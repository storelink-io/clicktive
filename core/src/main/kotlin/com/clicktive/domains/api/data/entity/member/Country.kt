package com.clicktive.domains.api.data.entity.member

import com.clicktive.domains.api.data.entity.BaseEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "ctv_mb_country")
@NoArg
class Country (
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("국가 번호")
    var countryNo: Long?,

    @Comment("국가 명")
    var countryName: String?,

    @Comment("통화 명")
    var currencyName: String?,

    @Comment("통화")
    var currency: String?,

    @Comment("국가 상태 코드")
    var countryStateCd: String?,
) : BaseEntity() {
    @Comment("환율")
    var exchangeRate: Double? = null
}