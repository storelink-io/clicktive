package com.clicktive.domains.api.data.entity.ad

import com.clicktive.domains.api.data.entity.BaseEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "ctv_ad_dashboard")
@NoArg
class AdDashboard (
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("광고 대시보드 번호")
    var adDashboardNo: Long?,

    @Comment("브랜드 번호")
    var brandNo: Long,

    @Comment("국가 번호")
    var countryNo: Long,

    @Comment("대상 월")
    var month: String,

    @Comment("월간 운영 코멘트")
    var operationMemo: String?,

    @Comment("스토어 성과 및 광고 코멘트")
    var storeAdMemo: String?,

    @Comment("캠페인 결과 코멘트")
    var campaignMemo: String?,

    @Comment("판매 상품 코멘트")
    var salesMemo: String?,

    @Comment("키워드 광고 코멘트")
    var adKeywordMemo: String?
) : BaseEntity()
