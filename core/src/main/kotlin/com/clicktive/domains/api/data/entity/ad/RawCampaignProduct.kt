package com.clicktive.domains.api.data.entity.ad

import com.clicktive.domains.api.data.entity.BaseCreateEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "ctv_ad_raw_campaign_product")
@NoArg
class RawCampaignProduct(
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("RAW 광고 상품 번호")
    var rawAdProductNo: Long?,

    @Comment("브랜드 번호")
    var brandNo: Long,

    @Comment("국가 번호")
    var countryNo: Long,

    @Comment("대상 월")
    var month: String,

    @Comment("시작 날짜")
    var startDate: String?,

    @Comment("종료 날짜")
    var endDate: String?,

    @Comment("포트폴리오 이름")
    var portfolioName: String?,

    @Comment("통화")
    var currency: String?,

    @Comment("캠페인 이름")
    var campaignName: String?,

    @Comment("광고 그룹 이름")
    var campaignGroup: String?,

    @Comment("광고된 SKU")
    var sku: String?,

    @Comment("광고된 ASIN")
    var asin: String?,

    @Comment("광고 노출 수")
    var viewNum: Int,

    @Comment("클릭 수")
    var clickNum: Int,

    @Comment("CTR(클릭률)")
    var ctr: Float,

    @Comment("CPC(클릭당 비용)")
    var cpc: Float,

    @Comment("지출")
    var expenseAmt: Float,

    @Comment("7일 총 판매")
    var salesTotal7Amt: Float,

    @Comment("총 ACOS(판매 광고 비용)")
    var acos: Float,

    @Comment("총 ROAS(광고 수익률)")
    var roas: Float,

    @Comment("7일 총 주문(건수)")
    var orderToal7Num: Int,

    @Comment("7일 총 수량(개수)")
    var orderTotal7Qty: Int,

    @Comment("7일 전환율")
    var conversion7Rate: Float,

    @Comment("7일 광고된 SKU 수량(개수)")
    var adSku7Num: Int,

    @Comment("7일 기타 SKU 수량(개수)")
    var etcSku7Num: Int,

    @Comment("7일 광고된 SKU 판매")
    var adSkuSales7Amt: Float,

    @Comment("7일 기타 SKU 판매")
    var etcSkuSales7Amt: Float
) : BaseCreateEntity()