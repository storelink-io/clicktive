package com.clicktive.domains.api.data.entity.member

import com.clicktive.domains.api.data.entity.BaseCreateEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.io.Serializable


@NoArg
data class BrandCountryPk(
    var brandNo: Long,
    var countryNo: Long,
) : Serializable

@Entity
@Table(name = "ctv_mb_brand_country")
@IdClass(BrandCountryPk::class)
@NoArg
class BrandCountry (
    @Id
    @org.springframework.data.annotation.Id
    @Comment("브랜드 번호")
    var brandNo: Long,

    @Id
    @org.springframework.data.annotation.Id
    @Comment("국가 번호")
    var countryNo: Long,

) : BaseCreateEntity() {
}
