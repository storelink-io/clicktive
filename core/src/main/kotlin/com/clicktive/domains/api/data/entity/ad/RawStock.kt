package com.clicktive.domains.api.data.entity.ad

import com.clicktive.domains.api.data.entity.BaseEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "ctv_ad_raw_stock")
@NoArg
class RawStock (
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("RAW 재고 번호")
    var rawStockNo: Long?,

    @Comment("브랜드 번호")
    var brandNo: Long,

    @Comment("국가 번호")
    var countryNo: Long,

    @Comment("대상 월")
    var month: String?,

    @Comment("sku")
    var sku: String?,

    @Comment("fnsku")
    var fnsku: String?,

    @Comment("asin")
    var asin: String?,

    @Comment("상품 명")
    var productName: String?,

    @Comment("상품 상태")
    var condition: String?,

    @Comment("판매가")
    var priceAmt: Float,

    @Comment("mfn 여부")
    var mfnYn: String?,

    @Comment("mfn 수량")
    var mfnQty: Int,

    @Comment("afn 여부")
    var afnYn: String?,

    @Comment("afn 창고 수량")
    var afnWareQty: Int,

    @Comment("afn 수량")
    var afnQty: Int,

    @Comment("afn 판매 불가 수량")
    var afnUnsellQty: Int,

    @Comment("afn 예약 수량")
    var afnReservedQty: Int,

    @Comment("afn 총 수량")
    var afnTotalQty: Int,

    @Comment("perUnitVolume")
    var perUnitVolume: Float,

    @Comment("afn 작업량")
    var afnWorkQty: Int,

    @Comment("afn 배송량")
    var afnShipQty: Int,

    @Comment("afn 수신량")
    var afnReceiveQty: Int,

    @Comment("afn 연구 수량")
    var afnResearchQty: Int,

    @Comment("afn 예약 공급량")
    var afnFutureReservedSupplyQty: Int,

    @Comment("afn 구매 가능 공급량")
    var afnFutureBuySupplyQty: Int,

) : BaseEntity() {
}
