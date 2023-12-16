package com.clicktive.domains.api.data.entity.ad

import com.clicktive.domains.api.data.entity.BaseEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment


@Entity
@Table(name = "ctv_ad_raw_sales")
@NoArg
class RawSales (
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("RAW 매출 번호")
    var rawSalesNo: Long?,

    @Comment("브랜드 번호")
    var brandNo: Long,

    @Comment("국가 번호")
    var countryNo: Long,

    @Comment("대상 월")
    var month: String?,

    @Comment("주문 일자")
    var purchaseDate: String?,

    @Comment("주문한 상품 판매량")
    var salesAmt: Int,

    @Comment("주문한 상품 판매량 - B2B")
    var salesB2bAmt: Int,

    @Comment("상품 주문량")
    var orderQty: Int,

    @Comment("상품 주문량 - B2B")
    var orderB2bQty: Int,

    @Comment("총 주문 아이템")
    var orderItemQty: Int,

    @Comment("총 주문 아이템 - B2B")
    var orderItemB2bQty: Int,

    @Comment("주문 아이템당 평균 판매")
    var orderItemAvgAmt: Int,

    @Comment("주문 아이템당 평균 판매 - B2B")
    var orderItemB2bAvgAmt: Int,

    @Comment("주문 아이템당 평균 수량")
    var orderItemAvgQty: Int,

    @Comment("주문 아이템당 평균 수량 - B2B")
    var orderItemB2bAvgQty: Int,

    @Comment("평균 판매 가격")
    var avgSalesAmt: Int,

    @Comment("평균 판매 가격 - B2B")
    var avgSalesB2bAmt: Int,

    @Comment("페이지 조회수 - 모바일 앱")
    var pageViewAppNum: Int,

    @Comment("페이지 조회수 - 모바일 앱 - B2B")
    var pageViewAppB2bNum: Int,

    @Comment("페이지 조회수 - 브라우저")
    var pageViewBrowserNum: Int,

    @Comment("페이지 조회수 - 브라우저 - B2B")
    var pageViewBrowserB2bNum: Int,

    @Comment("페이지 조회수 - 총계")
    var pageViewTotalNum: Int,

    @Comment("페이지 조회수 - 총계 - B2B")
    var pageViewB2bTotalNum: Int,

    @Comment("세션 - 모바일 앱")
    var sessionAppNum: Int,

    @Comment("세션 - 모바일 앱 - B2B")
    var sessionAppB2bNum: Int,

    @Comment("세션 - 브라우저")
    var sessionBrowserNum: Int,

    @Comment("세션 - 브라우저 - B2B")
    var sessionBrowserB2bNum: Int,

    @Comment("세션 - 총계")
    var sessionTotalNum: Int,

    @Comment("세션 - 총계 - B2B")
    var sessionB2bTotalNum: Int,

    @Comment("주요 오퍼(바이 박스) 백분율")
    var mainOfferRate: Float,

    @Comment("추천 오퍼(바이 박스) 비율 - B2B")
    var recommandOfferB2bRate: Float,

    @Comment("주문 아이템 세션 비율")
    var orderItemSessionRate: Float,

    @Comment("주문 아이템 세션 비율 - B2B")
    var orderItemSessionB2bRate: Float,

    @Comment("상품 세션 비율")
    var productSessionRate: Float,

    @Comment("상품 세션 비율 - B2B")
    var productSessionB2bRate: Float,

    @Comment("평균 오퍼 수")
    var offerAvgNum: Int,

    @Comment("평균 상위 품목")
    var upperItemAvgNum: Int,

    @Comment("환불 상품")
    var claimQty: Int,

    @Comment("환불률")
    var claimRate: Float,

    @Comment("피드백 수신")
    var feedbackReceivedNum: Int,

    @Comment("부정적인 피드백 수신")
    var feedbackBadReceivedNum: Int,

    @Comment("수신된 부정적인 피드백 비율")
    var feedbackBadReceivedRate: Float,

    @Comment("보증된 A-to-Z 보증 클레임")
    var guaranteeCaimNum: Int,

    @Comment("청구 금액")
    var chargeAmt: Int,

    @Comment("발송된 상품 판매량")
    var sendedSalesAmt: Int,

    @Comment("상품 발송량")
    var productSendQty: Int,

    @Comment("발송된 주문")
    var sendedOrderQty: Int,

) : BaseEntity() {
}
