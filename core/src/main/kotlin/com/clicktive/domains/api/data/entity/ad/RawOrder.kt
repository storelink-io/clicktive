package com.clicktive.domains.api.data.entity.ad

import com.clicktive.domains.api.data.entity.BaseCreateEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "ctv_ad_raw_order")
@NoArg
class RawOrder (
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("RAW 주문 번호")
    var rawOrderNo: Long?,

    @Comment("브랜드 번호")
    var brandNo: Long,

    @Comment("국가 번호")
    var countryNo: Long,

    @Comment("대상 월")
    var month: String,

    @Comment("아마존 주문 아이디")
    var amazonOrerId: String?,

    @Comment("고객사 주문 아이디")
    var merchantOrderId: String?,

    @Comment("주문 일자")
    var purchaseDate: LocalDateTime?,

    @Comment("마지막 변경 일자")
    var updateDate: LocalDateTime?,

    @Comment("주문 상태")
    var orderStatus: String?,

    @Comment("이행 채널")
    var fulfillmentChannel: String?,

    @Comment("판매 채널")
    var salesChannel: String?,

    @Comment("주문 채널")
    var orderChannel: String?,

    @Comment("url")
    var url: String?,

    @Comment("배송 서비스 레벨")
    var shipServiceLevel: String?,

    @Comment("상품 명")
    var productName: String?,

    @Comment("sku")
    var sku: String?,

    @Comment("asin")
    var asin: String?,

    @Comment("아이템 상태")
    var itemState: String?,

    @Comment("수량")
    var orderQty: Int,

    @Comment("통화")
    var currency: String?,

    @Comment("판매가")
    var priceAmt: Float,

    @Comment("세금")
    var tax: Float,

    @Comment("배송비")
    var shippingPriceAmt: Float,

    @Comment("배송 세금")
    var shippingTax: Float,

    @Comment("선물 포장 비용")
    var giftWrapPriceAmt: Float,

    @Comment("선물 포장 세금")
    var giftWrapTaxAmt: Float,

    @Comment("프로모션 할인")
    var discount: Float,

    @Comment("배송 프로모션 할인")
    var shipDiscount: Float,

    @Comment("배송 도시")
    var shipCity: String?,

    @Comment("배송 주")
    var shipState: String?,

    @Comment("배송 우편 코드")
    var postalCode: String?,

    @Comment("배송 나라")
    var shipCountry: String?,

    @Comment("프로모션 ID")
    var promotionIds: String?,

    @Comment("비즈니스 주문 여부")
    var businessOrderYn: String?,

    @Comment("주문 번호")
    var orderNumber: String?,

    @Comment("가격 지정")
    var priceFixed: String?,

    @Comment("서명 확인 여부")
    var confirmSignatureYn: String?
) : BaseCreateEntity()
