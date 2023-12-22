package com.clicktive.domains.api.data.entity.ad

import com.clicktive.domains.api.data.entity.BaseCreateEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "ctv_ad_campaign_daily")
@NoArg
class CampaignDaily (
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("일별 캠페인 번호")
    var campaignDailyNo: Long?,

    @Comment("브랜드 번호")
    var brandNo: Long,

    @Comment("국가 번호")
    var countryNo: Long,

    @Comment("대상 월")
    var month: String,

    @Comment("캠페인 일자")
    var camaignDate: String?,

    @Comment("광고비")
    var adExpenseAmt: Float,

    @Comment("광고매출")
    var adSalesAmt: Float,

    @Comment("오가닉매출")
    var organicSalesAmt: Float,

    @Comment("TOTAL ROAS")
    var totalRoas: Float,

    @Comment("ROAS")
    var roas: Float,

    @Comment("ACOS")
    var acos: Float,

    @Comment("노출수")
    var viewNum: Int,

    @Comment("클릭수")
    var clickNum: Int,

    @Comment("PV")
    var pv: Int,

    @Comment("CTR")
    var ctr: Float,

    @Comment("CPC")
    var cpc: Float,

    @Comment("CPM")
    var cpm: Float,
) : BaseCreateEntity()
