package com.clicktive.domains.api.data.entity.ad

import com.clicktive.domains.api.data.entity.BaseEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "ctv_ad_raw_sales_product")
@NoArg
class RawSalesProduct (
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("RAW 상품 판매 번호")
    var rawSalesProductNo: Long?,

    @Comment("브랜드 번호")
    var brandNo: Long,

    @Comment("국가 번호")
    var countryNo: Long,

    @Comment("대상 월")
    var month: String?,

    @Comment("ASIN")
    var asin: String?,

    @Comment("제목")
    var productName: String?,

    @Comment("SKU")
    var sku: String?,

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

    @Comment("세션 비율 - 모바일 앱")
    var sessionAppRate: Float,

    @Comment("세션 비율 - 모바일 앱 - B2B")
    var sessionAppB2bRate: Float,

    @Comment("세션 비율 - 브라우저")
    var sessionBrowserRate: Float,

    @Comment("세션 비율 - 브라우저 - B2B")
    var sessionBrowserB2bRate: Float,

    @Comment("세션 비율 - 총계")
    var sessionTotalRate: Float,

    @Comment("세션 비율 - 총계 - B2B")
    var sessionB2bTotalRate: Float,

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

    @Comment("페이지 조회수 비율 - 모바일 앱")
    var pageViewAppRate: Float,

    @Comment("페이지 조회수 비율 - 모바일 앱 - B2B")
    var pageViewAppB2bRate: Float,

    @Comment("페이지 조회수 비율 - 브라우저")
    var pageViewBrowserRate: Float,

    @Comment("페이지 조회수 비율 - 브라우저 - B2B")
    var pageViewBrowserB2bRate: Float,

    @Comment("페이지 조회수 비율 - 총계")
    var pageViewTotalRate: Float,

    @Comment("페이지 조회수 비율 - 총계 - B2B")
    var pageViewB2bTotalRate: Float,

    @Comment("주요 오퍼(바이 박스) 백분율")
    var offerMainRate: Float,

    @Comment("추천 오퍼(바이 박스) 비율 - B2B")
    var offerRecommendB2bRate: Float,

    @Comment("상품 주문량")
    var orderQty: Int,

    @Comment("상품 주문량 - B2B")
    var orderB2bQty: Int,

    @Comment("상품 세션 비율")
    var productSessionRate: Float,

    @Comment("상품 세션 비율 - B2B")
    var productSessionB2bRate: Float,

    @Comment("주문한 상품 판매량")
    var salesAmt: Int,

    @Comment("주문한 상품 판매량 - B2B")
    var salesB2bAmt: Int,

    @Comment("총 주문 아이템")
    var orderItemTotalQty: Int,

    @Comment("총 주문 아이템 - B2B")
    var orderItemB2bTotalQty: Int,

) : BaseEntity() {
}