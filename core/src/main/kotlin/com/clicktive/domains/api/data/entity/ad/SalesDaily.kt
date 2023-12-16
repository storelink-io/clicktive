package com.clicktive.domains.api.data.entity.ad

import com.clicktive.domains.api.data.entity.BaseEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "ctv_ad_sales_daily")
@NoArg
class SalesDaily (
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("일별 매출 번호")
    var salesDailyNo: Long?,

    @Comment("브랜드 번호")
    var brandNo: Long,

    @Comment("국가 번호")
    var countryNo: Long,

    @Comment("대상 월")
    var month: String?,

    @Comment("주문 일자")
    var purchaseDate: String?,

    @Comment("매출 금액")
    var salesAmt: Float,

    @Comment("주문건수")
    var orderQty: Int,

    @Comment("객단가")
    var customerUnitAmt: Float,

    @Comment("방문객수")
    var visitorNum: Int,

    @Comment("구매전환율")
    var conversionRate: Float,

    @Comment("환불률")
    var refundRate: Float,

    @Comment("총 주문 아이템")
    var totalOrderItemQty: Int,

    @Comment("상품 주문량")
    var totalOrderQty: Int,

    @Comment("주문한 상품 판매량")
    var salesQty: Float,

    @Comment("평균 상품/주문 아이템")
    var avgSalesQtyRate: Float,

    @Comment("평균 판매/주문 아이템")
    var avgSalesAmtRate: Float,

) : BaseEntity() {
}
