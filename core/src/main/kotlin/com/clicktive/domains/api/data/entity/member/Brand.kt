package com.clicktive.domains.api.data.entity.member

import com.clicktive.domains.api.data.entity.BaseEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "ctv_mb_brand")
@NoArg
class Brand (
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("브랜드 번호")
    var brandNo: Long?,

    @Comment("회사 번호")
    var companyNo: Long,

    @Comment("브랜드 명")
    var brandName: String?,

    @Comment("브랜드 상태 코드")
    var brandStateCd: String?,

) : BaseEntity() {
}
