package com.clicktive.domains.api.service.excel

import com.clicktive.domains.api.constant.excel.CellDataConstants
import com.clicktive.domains.api.data.dto.excel.ReadExcelRequest
import com.clicktive.domains.api.data.entity.ad.RawOrder
import com.clicktive.domains.api.data.enum.ad.ResultFileTypeCd
import com.clicktive.domains.api.service.ad.OrderService
import com.clicktive.domains.api.service.ad.ResultFileService
import com.clicktive.framework.util.Mapper
import com.clicktive.framework.util.excel.ConvertExcelValue.toBoolean
import com.clicktive.framework.util.excel.ConvertExcelValue.toFloat
import com.clicktive.framework.util.excel.ConvertExcelValue.toInt
import com.clicktive.framework.util.excel.ConvertExcelValue.toLocalDateTime
import com.clicktive.framework.util.excel.ConvertExcelValue.toString
import com.clicktive.framework.util.excel.ReadExcel
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream

@Service
class ReadRawOrderExcelService(
    private val readExcel: ReadExcel,
    private val resultFileService: ResultFileService,
    private val orderService: OrderService
) {
    fun readRawOrder(
        file: MultipartFile?,
        readExcelRequest: ReadExcelRequest
    ): Int {
//        val file_ = FileInputStream("/Users/jenni/Desktop/Clicktive_Raw/주문처리보고서.xlsx")
//        val workbook: Workbook = XSSFWorkbook(file_)
        val workbook: Workbook = XSSFWorkbook(file!!.inputStream)
        val sheet = workbook.getSheetAt(0)

        val cellData = readExcel.setCellIndex(row = sheet.getRow(0), cellData = CellDataConstants.RawOrderCellData)
        val entityData = readExcel.getEntityData(sheet, cellData)

        val entity = entityData.mapNotNull { data ->
            RawOrder(
                rawOrderNo = null,
                brandNo = readExcelRequest.brandNo,
                countryNo = readExcelRequest.countryNo,
                month = readExcelRequest.month,
                amazonOrerId = data?.get("amazonOrerId").toString(),
                merchantOrderId = data?.get("merchantOrderId").toString(),
                purchaseDate = data?.get("purchaseDate").toLocalDateTime(),
                updateDate = data?.get("updateDate").toLocalDateTime(),
                orderStatus = data?.get("orderStatus").toString(),
                fulfillmentChannel = data?.get("fulfillmentChannel").toString(),
                salesChannel = data?.get("salesChannel").toString(),
                orderChannel = data?.get("orderChannel").toString(),
                url = data?.get("url").toString(),
                shipServiceLevel = data?.get("shipServiceLevel").toString(),
                productName = data?.get("productName").toString(),
                sku = data?.get("sku").toString(),
                asin = data?.get("asin").toString(),
                itemState = data?.get("itemState").toString(),
                orderQty = data?.get("orderQty").toInt(),
                currency = data?.get("currency").toString(),
                priceAmt = data?.get("priceAmt").toFloat(),
                tax = data?.get("tax").toFloat(),
                shippingPriceAmt = data?.get("shippingPriceAmt").toFloat(),
                shippingTax = data?.get("shippingTax").toFloat(),
                giftWrapPriceAmt = data?.get("giftWrapPriceAmt").toFloat(),
                giftWrapTaxAmt = data?.get("giftWrapTaxAmt").toFloat(),
                discount = data?.get("discount").toFloat(),
                shipDiscount = data?.get("shipDiscount").toFloat(),
                shipCity = data?.get("shipCity").toString(),
                shipState = data?.get("shipState").toString(),
                postalCode = data?.get("postalCode").toString(),
                shipCountry = data?.get("shipCountry").toString(),
                promotionIds = data?.get("promotionIds").toString(),
                businessOrderYn = data?.get("businessOrderYn").toBoolean(),
                orderNumber = data?.get("orderNumber").toString(),
                priceFixed = data?.get("priceFixed").toString(),
                confirmSignatureYn = data?.get("confirmSignatureYn").toBoolean()
            )
        }

        orderService.createRawOrder(Mapper.convertAll(entity))
        val rowNum = entity.size

        resultFileService.createResultFile(
            brandNo = readExcelRequest.brandNo,
            countryNo = readExcelRequest.countryNo,
            month = readExcelRequest.month,
            resultFileTypeCd = ResultFileTypeCd.RAW_SALES.detailCode,
            rowNum = rowNum
        )

        return rowNum
    }
}