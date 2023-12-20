package com.clicktive.domains.api.data.entity.member

import com.clicktive.domains.api.data.entity.BaseEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "ctv_mb_company")
@NoArg
class Company (
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회사 번호")
    var companyNo: Long?,

    @Comment("회사명")
    var companyName: String?,

    @Comment("회사 상태 코드")
    var companyStateCd: String?,

    @Comment("사업등록번호")
    var bizNumber: String?,

    @Comment("대표자명")
    var ceoName: String?,

    @Comment("회사 주소")
    var address: String?,

) : BaseEntity() {
}