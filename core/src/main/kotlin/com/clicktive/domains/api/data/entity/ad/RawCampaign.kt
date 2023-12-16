package com.clicktive.domains.api.data.entity.ad

import com.clicktive.domains.api.data.entity.BaseEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "ctv_ad_raw_campaign")
@NoArg
class RawCampaign (
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("RAW 광고 캠페인 번호")
    var rawCampaignNo: Long?,

    @Comment("브랜드 번호")
    var brandNo: Long,

    @Comment("국가 번호")
    var countryNo: Long,

    @Comment("대상 월")
    var month: String?,

    @Comment("캠페인 일자")
    var campaignDate: String?,

    @Comment("주")
    var campaignState: String?,

    @Comment("캠페인")
    var campaignName: String?,

    @Comment("상태")
    var campaignStatus: String?,

    @Comment("유형")
    var campaignType: String?,

    @Comment("타겟팅")
    var targeting: String?,

    @Comment("캠페인 입찰 전략")
    var campaignBid: String?,

    @Comment("시작일")
    var beginDate: String?,

    @Comment("종료일")
    var endDate: String?,

    @Comment("포트폴리오")
    var portfolioName: String?,

    @Comment("예산(USD)")
    var budgetAmt: Float,

    @Comment("검색 상위 IS")
    var searchUpperIs: String?,

    @Comment("비용 유형")
    var expenseType: String?,

    @Comment("노출수")
    var viewNum: Int,

    @Comment("클릭수")
    var clickNum: Int,

    @Comment("CTR(클릭률)")
    var ctr: Int,

    @Comment("지출액(USD)")
    var expenseAmt: Float,

    @Comment("CPC(USD)")
    var cpc: Int,

    @Comment("주문수")
    var orderQty: Int,

    @Comment("판매액(USD)")
    var salesAmt: Float,

    @Comment("ACOS")
    var acos: Int,

    @Comment("ROAS")
    var roas: Int,

    @Comment("브랜드 신규 주문수")
    var newOrderQty: Int,

    @Comment("브랜드 신규 주문 비율(%)")
    var newOrderRate: Float,

    @Comment("브랜드 신규 매출(USD)")
    var newSalesAmt: Int,

    @Comment("브랜드 신규 매출 비율(%)")
    var newSalesRate: Float,

    @Comment("조회 가능 노출수")
    var exposureNum: Int,

    @Comment("VCPM(USD)")
    var vcpm: Int,

    @Comment("비디오 1/4 시청")
    var video1Qrtr: Float,

    @Comment("비디오 2/4 시청")
    var video2Qrtr: Float,

    @Comment("비디오 3/4 시청")
    var video3Qrtr: Float,

    @Comment("비디오 전체 시청")
    var videoAllQrtr: Float,

    @Comment("비디오 음소거 해제")
    var muteCancel: Float,

    @Comment("VTR")
    var vtr: Float,

    @Comment("vCTR")
    var vctr: Float,

) : BaseEntity() {
}