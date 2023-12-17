package com.clicktive.domains.api.constant.excel

import com.clicktive.framework.util.excel.CellData
import org.apache.poi.ss.usermodel.CellType

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
}