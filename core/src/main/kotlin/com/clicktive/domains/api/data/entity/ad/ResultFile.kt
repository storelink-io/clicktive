package com.clicktive.domains.api.data.entity.ad

import com.clicktive.domains.api.data.entity.BaseEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "ctv_ad_result_file")
@NoArg
class ResultFile (
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("광고 성과 파일 번호")
    var resultFileNo: Long?,

    @Comment("브랜드 번호")
    var brandNo: Long,

    @Comment("국가 번호")
    var countryNo: Long,

    @Comment("대상 월")
    var month: String,

    @Comment("성과 파일 유형 코드")
    var resultFileTypeCd: String,

    @Comment("Row 수")
    var rowNum: Int,

    @Comment("등록 여부")
    var registerYn: String?,

) : BaseEntity() {
}