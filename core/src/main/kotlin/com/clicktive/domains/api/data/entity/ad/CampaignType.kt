package com.clicktive.domains.api.data.entity.ad

import com.clicktive.domains.api.data.entity.BaseCreateEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "ctv_ad_campaign_type")
@NoArg
class CampaignType (
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("캠페인 유형별 실적 번호")
    var campaignTypeNo: Long?,

    @Comment("브랜드 번호")
    var brandNo: Long,

    @Comment("국가 번호")
    var countryNo: Long,

    @Comment("대상 월")
    var month: String,

    @Comment("캠페인 유형 코드")
    var campaignTypeCd: String?,

    @Comment("광고비")
    var adExpenseAmt: Float,

    @Comment("광고매출")
    var adSalesAmt: Float,

    @Comment("ROAS")
    var roas: Float,

    @Comment("ACOS")
    var acos: Float,

    @Comment("CTR")
    var ctr: Float,

    @Comment("CPC")
    var cpc: Float
) : BaseCreateEntity()