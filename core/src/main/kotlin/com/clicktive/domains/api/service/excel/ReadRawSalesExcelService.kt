package com.clicktive.domains.api.service.excel

import com.clicktive.domains.api.constant.code.ResultFileTypeCd
import com.clicktive.domains.api.data.entity.ad.RawSales
import com.clicktive.domains.api.constant.excel.CellDataConstants
import com.clicktive.domains.api.data.dto.excel.ReadExcelRequestDto
import com.clicktive.domains.api.service.ad.ResultFileService
import com.clicktive.domains.api.service.ad.SalesService
import com.clicktive.framework.util.Mapper
import com.clicktive.framework.util.excel.ConvertExcelValue.toFloat
import com.clicktive.framework.util.excel.ConvertExcelValue.toInt
import com.clicktive.framework.util.excel.ConvertExcelValue.toString
import com.clicktive.framework.util.excel.ReadExcel
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class ReadRawSalesExcelService(
    private val readExcel: ReadExcel,
    private val resultFileService: ResultFileService,
    private val salesService: SalesService
) {
    fun readRawSales(
        file: MultipartFile,
        readExcelRequestDto: ReadExcelRequestDto
    ): Int {
        val workbook: Workbook = XSSFWorkbook(file.inputStream)
        val sheet = workbook.getSheetAt(0)

        val cellData = readExcel.setCellIndex(row = sheet.getRow(0), cellData = CellDataConstants.RawSalesCellData)
        val entityData = readExcel.getEntityData(sheet, cellData)

        val entity = entityData.mapNotNull { data ->
            val purchaseDate = getFormattedPurchaseDate(data?.get("purchaseDate").toString())

            RawSales(
                rawSalesNo = null,
                brandNo = readExcelRequestDto.brandNo,
                countryNo = readExcelRequestDto.countryNo,
                month = readExcelRequestDto.month,
                purchaseDate = purchaseDate,
                salesAmt = data?.get("salesAmt").toInt(),
                salesB2bAmt = data?.get("salesB2bAmt").toInt(),
                orderQty = data?.get("orderQty").toInt(),
                orderB2bQty = data?.get("orderB2bQty").toInt(),
                orderItemQty = data?.get("orderItemQty").toInt(),
                orderItemB2bQty = data?.get("orderItemB2bQty").toInt(),
                orderItemAvgAmt = data?.get("orderItemAvgAmt").toInt(),
                orderItemB2bAvgAmt = data?.get("orderItemB2bAvgAmt").toInt(),
                orderItemAvgQty = data?.get("orderItemAvgQty").toInt(),
                orderItemB2bAvgQty = data?.get("orderItemB2bAvgQty").toInt(),
                avgSalesAmt = data?.get("avgSalesAmt").toInt(),
                avgSalesB2bAmt = data?.get("avgSalesB2bAmt").toInt(),
                pageViewAppNum = data?.get("pageViewAppNum").toInt(),
                pageViewAppB2bNum = data?.get("pageViewAppB2bNum").toInt(),
                pageViewBrowserNum = data?.get("pageViewBrowserNum").toInt(),
                pageViewBrowserB2bNum = data?.get("pageViewBrowserB2bNum").toInt(),
                pageViewTotalNum = data?.get("pageViewTotalNum").toInt(),
                pageViewB2bTotalNum = data?.get("pageViewB2bTotalNum").toInt(),
                sessionAppNum = data?.get("sessionAppNum").toInt(),
                sessionAppB2bNum = data?.get("sessionAppB2bNum").toInt(),
                sessionBrowserNum = data?.get("sessionBrowserNum").toInt(),
                sessionBrowserB2bNum = data?.get("sessionBrowserB2bNum").toInt(),
                sessionTotalNum = data?.get("sessionTotalNum").toInt(),
                sessionB2bTotalNum = data?.get("sessionB2bTotalNum").toInt(),
                mainOfferRate = data?.get("mainOfferRate").toFloat(),
                recommandOfferB2bRate = data?.get("recommandOfferB2bRate").toFloat(),
                orderItemSessionRate = data?.get("orderItemSessionRate").toFloat(),
                orderItemSessionB2bRate = data?.get("orderItemSessionB2bRate").toFloat(),
                productSessionRate = data?.get("productSessionRate").toFloat(),
                productSessionB2bRate = data?.get("productSessionB2bRate").toFloat(),
                offerAvgNum = data?.get("offerAvgNum").toInt(),
                upperItemAvgNum = data?.get("upperItemAvgNum").toInt(),
                claimQty = data?.get("claimQty").toInt(),
                claimRate = data?.get("claimRate").toFloat(),
                feedbackReceivedNum = data?.get("feedbackReceivedNum").toInt(),
                feedbackBadReceivedNum = data?.get("feedbackBadReceivedNum").toInt(),
                feedbackBadReceivedRate = data?.get("feedbackBadReceivedRate").toFloat(),
                guaranteeCaimNum = data?.get("guaranteeCaimNum").toInt(),
                chargeAmt = data?.get("chargeAmt").toInt(),
                sendedSalesAmt = data?.get("sendedSalesAmt").toInt(),
                productSendQty = data?.get("productSendQty").toInt(),
                sendedOrderQty = data?.get("sendedOrderQty").toInt()
            )
        }

        salesService.createRawSales(Mapper.convertAll(entity))
        val rowNum = entity.size

        resultFileService.createResultFile(
            brandNo = readExcelRequestDto.brandNo,
            countryNo = readExcelRequestDto.countryNo,
            month = readExcelRequestDto.month,
            resultFileTypeCd = ResultFileTypeCd.RAW_SALES.detailCode,
            rowNum = rowNum
        )

        return rowNum
    }

    private fun getFormattedPurchaseDate(purchaseDate: String): String? {
        val extractedPurchaseDate = purchaseDate.replace(".", "").replace(" ", "")
        val parsedPurchaseDate = LocalDate.parse(extractedPurchaseDate, DateTimeFormatter.ofPattern("yyMMdd"))

        return parsedPurchaseDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }
}