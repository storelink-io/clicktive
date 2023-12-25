package com.clicktive.domains.api.data.entity.member

import com.clicktive.domains.api.data.entity.BaseCreateEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.io.Serializable

@NoArg
data class BrandMemberPk(
    var brandNo: Long,
    var memberNo: Long,
) : Serializable

@Entity
@Table(name = "ctv_mb_brand_member")
@IdClass(BrandMemberPk::class)
@NoArg
class BrandMember (
    @Id
    @org.springframework.data.annotation.Id
    @Comment("브랜드 번호")
    var brandNo: Long,

    @Id
    @org.springframework.data.annotation.Id
    @Comment("회원 번호")
    var memberNo: Long,

) : BaseCreateEntity() {
}