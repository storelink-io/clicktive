package com.clicktive.domains.api.constant.excel

import com.clicktive.framework.util.excel.CellData
import org.apache.poi.ss.usermodel.CellType

// TODO 전체적으로 cellType, isRequired 체크
object CellDataConstants {
    val RawSalesCellData = listOf(
        CellData(cellName = listOf("날짜", "Date"), cellType = CellType.STRING, isRequired = true, columnName = "purchaseDate"),
        CellData(cellName = listOf("주문한 상품 판매량", "Ordered Product Sales"), cellType = CellType.NUMERIC, isRequired = true, columnName = "salesAmt"),
        CellData(cellName = listOf("주문한 상품 판매량 - B2B", "Ordered Product Sales - B2B"), cellType = CellType.NUMERIC, isRequired = true, columnName = "salesB2bAmt"),
        CellData(cellName = listOf("상품 주문량", "Units Ordered"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderQty"),
        CellData(cellName = listOf("상품 주문량 - B2B", "Units Ordered - B2B"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderB2bQty"),
        CellData(cellName = listOf("총 주문 아이템", "Total Order Items"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderItemQty"),
        CellData(cellName = listOf("총 주문 아이템 - B2B", "Total Order Items - B2B"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderItemB2bQty"),
        CellData(cellName = listOf("주문 아이템당 평균 판매", "Average Sales per Order Item"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderItemAvgAmt"),
        CellData(cellName = listOf("주문 아이템당 평균 판매 - B2B", "Average Sales per Order Item - B2B"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderItemB2bAvgAmt"),
        CellData(cellName = listOf("주문 아이템당 평균 수량", "Average Units per Order Item"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderItemAvgQty"),
        CellData(cellName = listOf("주문 아이템당 평균 수량 - B2B", "Average Units per Order Item - B2B"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderItemB2bAvgQty"),
        CellData(cellName = listOf("평균 판매 가격", "Average Selling Price"), cellType = CellType.NUMERIC, isRequired = true, columnName = "avgSalesAmt"),
        CellData(cellName = listOf("평균 판매 가격 - B2B", "Average Selling Price - B2B"), cellType = CellType.NUMERIC, isRequired = true, columnName = "avgSalesB2bAmt"),
        CellData(cellName = listOf("페이지 조회수 - 모바일 앱", "Page Views - Mobile App"), cellType = CellType.NUMERIC, isRequired = true, columnName = "pageViewAppNum"),
        CellData(cellName = listOf("페이지 조회수 - 모바일 앱 - B2B", "Page Views - Mobile APP - B2B"), cellType = CellType.NUMERIC, isRequired = true, columnName = "pageViewAppB2bNum"),
        CellData(cellName = listOf("페이지 조회수 - 브라우저", "Page Views - Browser"), cellType = CellType.NUMERIC, isRequired = true, columnName = "pageViewBrowserNum"),
        CellData(cellName = listOf("페이지 조회수 - 브라우저 - B2B", "Page Views - Browser - B2B"), cellType = CellType.NUMERIC, isRequired = true, columnName = "pageViewBrowserB2bNum"),
        CellData(cellName = listOf("페이지 조회수 - 총계", "Page Views - Total"), cellType = CellType.NUMERIC, isRequired = true, columnName = "pageViewTotalNum"),
        CellData(cellName = listOf("페이지 조회수 - 총계 - B2B", "Page Views - Total - B2B"), cellType = CellType.NUMERIC, isRequired = true, columnName = "pageViewB2bTotalNum"),
        CellData(cellName = listOf("세션 - 모바일 앱", "Sessions - Mobile App"), cellType = CellType.NUMERIC, isRequired = true, columnName = "sessionAppNum"),
        CellData(cellName = listOf("세션 - 모바일 앱 - B2B", "Sessions - Mobile APP - B2B"), cellType = CellType.NUMERIC, isRequired = true, columnName = "sessionAppB2bNum"),
        CellData(cellName = listOf("세션 - 브라우저", "Sessions - Browser"), cellType = CellType.NUMERIC, isRequired = true, columnName = "sessionBrowserNum"),
        CellData(cellName = listOf("세션 - 브라우저 - B2B", "Sessions - Browser - B2B"), cellType = CellType.NUMERIC, isRequired = true, columnName = "sessionBrowserB2bNum"),
        CellData(cellName = listOf("세션 - 총계", "Sessions - Total"), cellType = CellType.NUMERIC, isRequired = true, columnName = "sessionTotalNum"),
        CellData(cellName = listOf("세션 - 총계 - B2B", "Sessions - Total - B2B"), cellType = CellType.NUMERIC, isRequired = true, columnName = "sessionB2bTotalNum"),
        CellData(cellName = listOf("주요 오퍼(바이 박스) 백분율", "Featured Offer (Buy Box) Percentage"), cellType = CellType.NUMERIC, isRequired = true, columnName = "mainOfferRate"),
        CellData(cellName = listOf("추천 오퍼(바이 박스) 비율 - B2B", "Order Item Session Percentage"), cellType = CellType.NUMERIC, isRequired = true, columnName = "recommandOfferB2bRate"),
        CellData(cellName = listOf("주문 아이템 세션 비율", "Order Item Session Percentage"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderItemSessionRate"),
        CellData(cellName = listOf("주문 아이템 세션 비율 - B2B", "Order Item Session Percentage - B2B"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderItemSessionB2bRate"),
        CellData(cellName = listOf("상품 세션 비율", "Unit Session Percentage"), cellType = CellType.NUMERIC, isRequired = true, columnName = "productSessionRate"),
        CellData(cellName = listOf("상품 세션 비율 - B2B", "Unit Session Percentage - B2B"), cellType = CellType.NUMERIC, isRequired = true, columnName = "productSessionB2bRate"),
        CellData(cellName = listOf("평균 오퍼 수", "Average Offer Count"), cellType = CellType.NUMERIC, isRequired = true, columnName = "offerAvgNum"),
        CellData(cellName = listOf("평균 상위 품목", "Average Parent Items"), cellType = CellType.NUMERIC, isRequired = true, columnName = "upperItemAvgNum"),
        CellData(cellName = listOf("환불 상품", "Units Refunded"), cellType = CellType.NUMERIC, isRequired = true, columnName = "claimQty"),
        CellData(cellName = listOf("환불률", "Refund Rate"), cellType = CellType.NUMERIC, isRequired = true, columnName = "claimRate"),
        CellData(cellName = listOf("피드백 수신", "Feedback Received"), cellType = CellType.NUMERIC, isRequired = true, columnName = "feedbackReceivedNum"),
        CellData(cellName = listOf("부정적인 피드백 수신", "Negative Feedback Received"), cellType = CellType.NUMERIC, isRequired = true, columnName = "feedbackBadReceivedNum"),
        CellData(cellName = listOf("수신된 부정적인 피드백 비율", "Received Negative Feedback Rate"), cellType = CellType.NUMERIC, isRequired = true, columnName = "feedbackBadReceivedRate"),
        CellData(cellName = listOf("보증된 A-to-Z 보증 클레임", "A-to-z Claims Granted"), cellType = CellType.NUMERIC, isRequired = true, columnName = "guaranteeCaimNum"),
        CellData(cellName = listOf("청구 금액", "Claims Amount"), cellType = CellType.NUMERIC, isRequired = true, columnName = "chargeAmt"),
        CellData(cellName = listOf("발송된 상품 판매량", "Shipped Product Sales"), cellType = CellType.NUMERIC, isRequired = true, columnName = "sendedSalesAmt"),
        CellData(cellName = listOf("상품 발송량", "Units Shipped"), cellType = CellType.NUMERIC, isRequired = true, columnName = "productSendQty"),
        CellData(cellName = listOf("발송된 주문", "Orders Shipped"), cellType = CellType.NUMERIC, isRequired = true, columnName = "sendedOrderQty")
    )

    // TODO - B2B 들어가는 컬럼 엑셀 매칭 불가능(샘플 엑셀)
    val RawSalesProductCellData = listOf(
        CellData(cellName = listOf("(상위) ASIN", "(Parent) ASIN"), cellType = CellType.STRING, isRequired = true, columnName = "asinUpper"),
        CellData(cellName = listOf("(하위) ASIN", "(Child) ASIN"), cellType = CellType.STRING, isRequired = true, columnName = "asin"),
        CellData(cellName = listOf("제목", "Title"), cellType = CellType.STRING, isRequired = true, columnName = "productName"),
        CellData(cellName = listOf("SKU", "SKU"), cellType = CellType.STRING, isRequired = true, columnName = "sku"),
        CellData(cellName = listOf("세션 - 모바일 앱", "Sessions - Mobile App"), cellType = CellType.NUMERIC, isRequired = true, columnName = "sessionAppNum"),
        CellData(cellName = listOf("세션 - 모바일 앱 - B2B", "Sessions – Mobile APP – B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "sessionAppB2bNum"),
        CellData(cellName = listOf("세션 - 브라우저", "Sessions - Browser"), cellType = CellType.NUMERIC, isRequired = true, columnName = "sessionBrowserNum"),
        CellData(cellName = listOf("세션 - 브라우저 - B2B", "Sessions – Browser – B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "sessionBrowserB2bNum"),
        CellData(cellName = listOf("세션 - 총계", "Sessions - Total"), cellType = CellType.NUMERIC, isRequired = true, columnName = "sessionTotalNum"),
        CellData(cellName = listOf("세션 - 총계 - B2B", "Sessions – Total – B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "sessionB2bTotalNum"),
        CellData(cellName = listOf("세션 비율 - 모바일 앱", "Session Percentage - Mobile App"), cellType = CellType.NUMERIC, isRequired = true, columnName = "sessionAppRate"),
        CellData(cellName = listOf("세션 비율 - 모바일 앱 - B2B", "Session Percentage – Mobile APP – B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "sessionAppB2bRate"),
        CellData(cellName = listOf("세션 비율 - 브라우저", "Session Percentage - Browser"), cellType = CellType.NUMERIC, isRequired = true, columnName = "sessionBrowserRate"),
        CellData(cellName = listOf("세션 비율 - 브라우저 - B2B", "Session Percentage – Browser – B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "sessionBrowserB2bRate"),
        CellData(cellName = listOf("세션 비율 - 총계", "Session Percentage - Total"), cellType = CellType.NUMERIC, isRequired = true, columnName = "sessionTotalRate"),
        CellData(cellName = listOf("세션 비율 - 총계 - B2B", "Session Percentage – Total – B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "sessionB2bTotalRate"),
        CellData(cellName = listOf("페이지 조회수 - 모바일 앱", "Page Views - Mobile App"), cellType = CellType.NUMERIC, isRequired = true, columnName = "pageViewAppNum"),
        CellData(cellName = listOf("페이지 조회수 - 모바일 앱 - B2B", "Page Views – Mobile APP – B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "pageViewAppB2bNum"),
        CellData(cellName = listOf("페이지 조회수 - 브라우저", "Page Views - Browser"), cellType = CellType.NUMERIC, isRequired = true, columnName = "pageViewBrowserNum"),
        CellData(cellName = listOf("페이지 조회수 - 브라우저 - B2B", "Page Views – Browser – B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "pageViewBrowserB2bNum"),
        CellData(cellName = listOf("페이지 조회수 - 총계", "Page Views - Total"), cellType = CellType.NUMERIC, isRequired = true, columnName = "pageViewTotalNum"),
        CellData(cellName = listOf("페이지 조회수 - 총계 - B2B", "Page Views – Total – B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "pageViewB2bTotalNum"),
        CellData(cellName = listOf("페이지 조회수 비율 - 모바일 앱", "Page Views Percentage - Mobile App"), cellType = CellType.NUMERIC, isRequired = true, columnName = "pageViewAppRate"),
        CellData(cellName = listOf("페이지 조회수 비율 - 모바일 앱 - B2B", "Page Views Percentage – Mobile App – B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "pageViewAppB2bRate"),
        CellData(cellName = listOf("페이지 조회수 비율 - 브라우저", "Page Views Percentage - Browser"), cellType = CellType.NUMERIC, isRequired = true, columnName = "pageViewBrowserRate"),
        CellData(cellName = listOf("페이지 조회수 비율 - 브라우저 - B2B", "Page Views Percentage – Browser – B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "pageViewBrowserB2bRate"),
        CellData(cellName = listOf("페이지 조회수 비율 - 총계", "Page Views Percentage - Total"), cellType = CellType.NUMERIC, isRequired = true, columnName = "pageViewTotalRate"),
        CellData(cellName = listOf("페이지 조회수 비율 - 총계 - B2B", "Page Views Percentage – Total – B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "pageViewB2bTotalRate"),
        CellData(cellName = listOf("주요 오퍼(바이 박스) 백분율", "Featured Offer (Buy Box) Percentage"), cellType = CellType.NUMERIC, isRequired = true, columnName = "offerMainRate"),
        CellData(cellName = listOf("추천 오퍼(바이 박스) 비율 - B2B", "Featured Offer (Buy Box) Percentage – B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "offerRecommendB2bRate"),
        CellData(cellName = listOf("상품 주문량", "Units ordered"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderQty"),
        CellData(cellName = listOf("상품 주문량 - B2B", "Units ordered - B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "orderB2bQty"),
        CellData(cellName = listOf("상품 세션 비율", "Unit session percentage"), cellType = CellType.NUMERIC, isRequired = true, columnName = "productSessionRate"),
        CellData(cellName = listOf("상품 세션 비율 - B2B", "Unit session percentage - B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "productSessionB2bRate"),
        CellData(cellName = listOf("주문한 상품 판매량", "Ordered product sales"), cellType = CellType.NUMERIC, isRequired = true, columnName = "salesAmt"),
        CellData(cellName = listOf("주문한 상품 판매량 - B2B", "Ordered product sales - B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "salesB2bAmt"),
        CellData(cellName = listOf("총 주문 아이템", "Total order items"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderItemTotalQty"),
        CellData(cellName = listOf("총 주문 아이템 - B2B", "Total order items - B2B"), cellType = CellType.NUMERIC, isRequired = false, columnName = "orderItemB2bTotalQty")
    )

    val RawCampaignProductCellData = listOf(
        // TODO 시작 날짜, 종료 날짜 NUMERIC or STRING 체크
        CellData(cellName = listOf("시작 날짜", "Start Date"), cellType = CellType.NUMERIC, isRequired = true, columnName = "startDate"),
        CellData(cellName = listOf("종료 날짜", "End Date"), cellType = CellType.NUMERIC, isRequired = true, columnName = "endDate"),
        CellData(cellName = listOf("포트폴리오 이름", "Portfolio name"), cellType = CellType.STRING, isRequired = true, columnName = "portfolioName"),
        CellData(cellName = listOf("통화", "Currency"), cellType = CellType.STRING, isRequired = true, columnName = "currency"),
        CellData(cellName = listOf("캠페인 이름", "Campaign Name"), cellType = CellType.STRING, isRequired = true, columnName = "campaignName"),
        CellData(cellName = listOf("광고 그룹 이름", "Ad Group Name"), cellType = CellType.STRING, isRequired = true, columnName = "campaignGroup"),
        CellData(cellName = listOf("광고된 SKU", "Advertised SKU"), cellType = CellType.STRING, isRequired = true, columnName = "sku"),
        CellData(cellName = listOf("광고된 ASIN", "Advertised ASIN"), cellType = CellType.STRING, isRequired = true, columnName = "asin"),
        CellData(cellName = listOf("광고 노출 수", "Impressions"), cellType = CellType.NUMERIC, isRequired = true, columnName = "viewNum"),
        CellData(cellName = listOf("클릭 수", "Clicks"), cellType = CellType.NUMERIC, isRequired = true, columnName = "clickNum"),
        CellData(cellName = listOf("CTR(클릭률)", "Click-Thru Rate (CTR)"), cellType = CellType.NUMERIC, isRequired = false, columnName = "ctr"),
        CellData(cellName = listOf("CPC(클릭당 비용)", "Cost Per Click (CPC)"), cellType = CellType.NUMERIC, isRequired = false, columnName = "cpc"),
        CellData(cellName = listOf("지출", "Spend"), cellType = CellType.NUMERIC, isRequired = true, columnName = "expenseAmt"),
        CellData(cellName = listOf("7일 총 판매", "7 Day Total Sales"), cellType = CellType.NUMERIC, isRequired = true, columnName = "salesTotal7Amt"),
        CellData(cellName = listOf("총 ACOS(판매 광고 비용)", "Total Advertising Cost of Sales (ACOS)"), cellType = CellType.NUMERIC, isRequired = false, columnName = "acos"),
        CellData(cellName = listOf("총 ROAS(광고 수익률)", "Total Return on Advertising Spend (ROAS)"), cellType = CellType.NUMERIC, isRequired = false, columnName = "roas"),
        CellData(cellName = listOf("7일 총 주문(건수)", "7 Day Total Orders (#)"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderToal7Num"),
        CellData(cellName = listOf("7일 총 수량(개수)", "7 Day Total Units (#)"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderTotal7Qty"),
        CellData(cellName = listOf("7일 전환율", "7 Day Conversion Rate"), cellType = CellType.NUMERIC, isRequired = false, columnName = "conversion7Rate"),
        CellData(cellName = listOf("7일 광고된 SKU 수량(개수)", "7 Day Advertised SKU Units (#)"), cellType = CellType.NUMERIC, isRequired = true, columnName = "adSku7Num"),
        CellData(cellName = listOf("7일 기타 SKU 수량(개수)", "7 Day Other SKU Units (#)"), cellType = CellType.NUMERIC, isRequired = true, columnName = "etcSku7Num"),
        CellData(cellName = listOf("7일 광고된 SKU 판매", "7 Day Advertised SKU Sales"), cellType = CellType.NUMERIC, isRequired = true, columnName = "adSkuSales7Amt"),
        CellData(cellName = listOf("7일 기타 SKU 판매", "7 Day Other SKU Sales"), cellType = CellType.NUMERIC, isRequired = true, columnName = "etcSkuSales7Amt")
    )

    val RawCampaignCellData = listOf(
        CellData(cellName = listOf("캠페인 일자", "Campaign Date"), cellType = CellType.NUMERIC, isRequired = false, columnName = "campaignDate"),
        CellData(cellName = listOf("주", "State"), cellType = CellType.STRING, isRequired = true, columnName = "campaignState"),
        CellData(cellName = listOf("캠페인", "Campaigns"), cellType = CellType.STRING, isRequired = true, columnName = "campaignName"),
        CellData(cellName = listOf("상태", "Status"), cellType = CellType.STRING, isRequired = true, columnName = "campaignStatus"),
        CellData(cellName = listOf("유형", "Type"), cellType = CellType.STRING, isRequired = true, columnName = "campaignType"),
        CellData(cellName = listOf("타겟팅", "Targeting"), cellType = CellType.STRING, isRequired = true, columnName = "targeting"),
        CellData(cellName = listOf("캠페인 입찰 전략", "Campaign bidding strategy"), cellType = CellType.STRING, isRequired = false, columnName = "campaignBid"),
        // TODO 시작 날짜, 종료 날짜 NUMERIC or STRING 체크
        CellData(cellName = listOf("시작일", "Start date"), cellType = CellType.NUMERIC, isRequired = true, columnName = "beginDate"),
        CellData(cellName = listOf("종료일", "End date"), cellType = CellType.NUMERIC, isRequired = false, columnName = "endDate"),
        CellData(cellName = listOf("포트폴리오", "Portfolio"), cellType = CellType.STRING, isRequired = true, columnName = "portfolioName"),
        // TODO 지출액, 예산 뒤에 통화 고정인지 확인 필요
        CellData(cellName = listOf("예산(JPY)", "Budget(USD)"), cellType = CellType.NUMERIC, isRequired = false, columnName = "budgetAmt"),
        CellData(cellName = listOf("검색 상위 IS", "Top-of-search IS"), cellType = CellType.STRING, isRequired = false, columnName = "searchUpperIs"),
        CellData(cellName = listOf("비용 유형", "Cost type"), cellType = CellType.STRING, isRequired = true, columnName = "expenseType"),
        CellData(cellName = listOf("노출수", "Impressions"), cellType = CellType.NUMERIC, isRequired = true, columnName = "viewNum"),
        CellData(cellName = listOf("클릭수", "Clicks"), cellType = CellType.NUMERIC, isRequired = true, columnName = "clickNum"),
        CellData(cellName = listOf("CTR(클릭률)", "CTR"), cellType = CellType.NUMERIC, isRequired = true, columnName = "ctr"),
        // TODO 지출액, CPC 뒤에 통화 고정인지 확인 필요
        CellData(cellName = listOf("지출액(JPY)", "Spend(USD)"), cellType = CellType.NUMERIC, isRequired = false, columnName = "expenseAmt"),
        CellData(cellName = listOf("CPC(JPY)", "CPC(USD)"), cellType = CellType.NUMERIC, isRequired = true, columnName = "cpc"),
        CellData(cellName = listOf("주문수", "Orders"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderQty"),
        // TODO 판매액 뒤에 통화 고정인지 뭔지 필요
        CellData(cellName = listOf("판매액(JPY)", "Sales(USD)"), cellType = CellType.NUMERIC, isRequired = false, columnName = "salesAmt"),
        CellData(cellName = listOf("ACOS", "ACOS"), cellType = CellType.NUMERIC, isRequired = true, columnName = "acos"),
        CellData(cellName = listOf("ROAS", "ROAS"), cellType = CellType.NUMERIC, isRequired = true, columnName = "roas"),
        CellData(cellName = listOf("브랜드 신규 주문수", "NTB orders"), cellType = CellType.NUMERIC, isRequired = true, columnName = "newOrderQty"),
        CellData(cellName = listOf("브랜드 신규 주문 비율(%)", "% of orders NTB"), cellType = CellType.NUMERIC, isRequired = true, columnName = "newOrderRate"),
        // TODO 브랜드 신규 매출 뒤에 통화 고정인지 확인 필요
        CellData(cellName = listOf("브랜드 신규 매출(JPY)", "NTB sales(USD)"), cellType = CellType.NUMERIC, isRequired = false, columnName = "newSalesAmt"),
        CellData(cellName = listOf("브랜드 신규 매출 비율(%)", "% of sales NTB"), cellType = CellType.NUMERIC, isRequired = true, columnName = "newSalesRate"),
        CellData(cellName = listOf("조회 가능 노출수", "Viewable impressions"), cellType = CellType.NUMERIC, isRequired = true, columnName = "exposureNum"),
        // TODO VCPM 매출 뒤에 통화 고정인지 뭔지 필요
        CellData(cellName = listOf("VCPM(JPY)", "VCPM(USD)"), cellType = CellType.NUMERIC, isRequired = false, columnName = "vcpm"),
        CellData(cellName = listOf("비디오 1/4 시청", "Video first quartile"), cellType = CellType.NUMERIC, isRequired = true, columnName = "video1Qrtr"),
        CellData(cellName = listOf("비디오 2/4 시청", "Video midpoint"), cellType = CellType.NUMERIC, isRequired = true, columnName = "video2Qrtr"),
        CellData(cellName = listOf("비디오 3/4 시청", "Video third quartile"), cellType = CellType.NUMERIC, isRequired = true, columnName = "video3Qrtr"),
        CellData(cellName = listOf("비디오 전체 시청", "Video complete"), cellType = CellType.NUMERIC, isRequired = true, columnName = "videoAllQrtr"),
        CellData(cellName = listOf("비디오 음소거 해제", "Video unmute"), cellType = CellType.NUMERIC, isRequired = true, columnName = "muteCancel"),
        CellData(cellName = listOf("VTR", "VTR"), cellType = CellType.NUMERIC, isRequired = true, columnName = "vtr"),
        CellData(cellName = listOf("vCTR", "vCTR"), cellType = CellType.NUMERIC, isRequired = true, columnName = "vctr")
    )

    val RawCampaignKeywordCellData = listOf(
        // TODO 시작 날짜, 종료 날짜 NUMERIC or STRING 체크
        CellData(cellName = listOf("시작 날짜", "Start Date"), cellType = CellType.NUMERIC, isRequired = true, columnName = "startDate"),
        CellData(cellName = listOf("종료 날짜", "End Date"), cellType = CellType.NUMERIC, isRequired = true, columnName = "endDate"),
        CellData(cellName = listOf("포트폴리오 이름", "Portfolio name"), cellType = CellType.STRING, isRequired = true, columnName = "portfolioName"),
        CellData(cellName = listOf("통화", "Currency"), cellType = CellType.STRING, isRequired = true, columnName = "currency"),
        CellData(cellName = listOf("캠페인 이름", "Campaign Name"), cellType = CellType.STRING, isRequired = true, columnName = "campaignName"),
        CellData(cellName = listOf("광고 그룹 이름", "Ad Group Name"), cellType = CellType.STRING, isRequired = true, columnName = "campaignGroup"),
        CellData(cellName = listOf("키워드", "Targeting"), cellType = CellType.STRING, isRequired = true, columnName = "keyword"),
        CellData(cellName = listOf("일치 유형", "Match Type"), cellType = CellType.STRING, isRequired = true, columnName = "accordanceType"),
        CellData(cellName = listOf("고객 검색어", "Customer Search Term"), cellType = CellType.STRING, isRequired = true, columnName = "keywordCustomer"),
        CellData(cellName = listOf("광고 노출 수", "Impressions"), cellType = CellType.NUMERIC, isRequired = true, columnName = "viewNum"),
        CellData(cellName = listOf("클릭 수", "Clicks"), cellType = CellType.NUMERIC, isRequired = true, columnName = "clickNum"),
        CellData(cellName = listOf("CTR(클릭률)", "Click-Thru Rate (CTR)"), cellType = CellType.NUMERIC, isRequired = true, columnName = "ctr"),
        CellData(cellName = listOf("CPC(클릭당 비용)", "Cost Per Click (CPC)"), cellType = CellType.NUMERIC, isRequired = true, columnName = "cpc"),
        CellData(cellName = listOf("지출", "Spend"), cellType = CellType.NUMERIC, isRequired = true, columnName = "expenseAmt"),
        CellData(cellName = listOf("7일 총 판매", "7 Day Total Sales"), cellType = CellType.NUMERIC, isRequired = true, columnName = "salesTotal7Amt"),
        CellData(cellName = listOf("총 ACOS(판매 광고 비용)", "Total Advertising Cost of Sales (ACOS)"), cellType = CellType.NUMERIC, isRequired = false, columnName = "acos"),
        CellData(cellName = listOf("총 ROAS(광고 수익률)", "Total Return on Advertising Spend (ROAS)"), cellType = CellType.NUMERIC, isRequired = true, columnName = "roas"),
        CellData(cellName = listOf("7일 총 주문(건수)", "7 Day Total Orders (#)"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderToal7Num"),
        CellData(cellName = listOf("7일 총 수량(개수)", "7 Day Total Units (#)"), cellType = CellType.NUMERIC, isRequired = true, columnName = "orderTotal7Qty"),
        CellData(cellName = listOf("7일 전환율", "7 Day Conversion Rate"), cellType = CellType.NUMERIC, isRequired = true, columnName = "conversion7Rate"),
        CellData(cellName = listOf("7일 광고된 SKU 수량(개수)", "7 Day Advertised SKU Units (#)"), cellType = CellType.NUMERIC, isRequired = true, columnName = "adSku7Num"),
        CellData(cellName = listOf("7일 기타 SKU 수량(개수)", "7 Day Other SKU Units (#)"), cellType = CellType.NUMERIC, isRequired = true, columnName = "etcSku7Num"),
        CellData(cellName = listOf("7일 광고된 SKU 판매", "7 Day Advertised SKU Sales"), cellType = CellType.NUMERIC, isRequired = true, columnName = "adSkuSales7Amt"),
        CellData(cellName = listOf("7일 기타 SKU 판매", "7 Day Other SKU Sales"), cellType = CellType.NUMERIC, isRequired = true, columnName = "etcSkuSales7Amt")
    )

    val RawOrderCellData = listOf<CellData>()

    val RawStockCellData = listOf<CellData>()
}

























