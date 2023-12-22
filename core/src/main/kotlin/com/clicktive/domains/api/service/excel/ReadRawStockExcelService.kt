package com.clicktive.domains.api.service.excel

import com.clicktive.domains.api.constant.excel.CellDataConstants
import com.clicktive.domains.api.data.dto.excel.ReadExcelRequest
import com.clicktive.domains.api.data.entity.ad.RawStock
import com.clicktive.domains.api.data.enum.ad.ResultFileTypeCd
import com.clicktive.domains.api.service.ad.ResultFileService
import com.clicktive.domains.api.service.ad.StockService
import com.clicktive.framework.util.Mapper
import com.clicktive.framework.util.excel.ConvertExcelValue.toString
import com.clicktive.framework.util.excel.ConvertExcelValue.toFloat
import com.clicktive.framework.util.excel.ConvertExcelValue.toInt
import com.clicktive.framework.util.excel.ConvertExcelValue.toNy
import com.clicktive.framework.util.excel.ReadExcel
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream

@Service
class ReadRawStockExcelService(
    private val readExcel: ReadExcel,
    private val resultFileService: ResultFileService,
    private val stockService: StockService
) {
    fun readRawStock(
        file: MultipartFile?,
        readExcelRequest: ReadExcelRequest
    ): Int {
//        val file_ = FileInputStream("/Users/jenni/Desktop/Clicktive_Raw/06_ inventory_RAW.xlsx")
//        val workbook: Workbook = XSSFWorkbook(file_)
        val workbook: Workbook = XSSFWorkbook(file!!.inputStream)
        val sheet = workbook.getSheetAt(0)

        val cellData = readExcel.setCellIndex(row = sheet.getRow(0), cellData = CellDataConstants.RawStockCellData)
        val entityData = readExcel.getEntityData(sheet, cellData)

        val entity = entityData.mapNotNull { data ->
            RawStock(
                rawStockNo = null,
                brandNo = readExcelRequest.brandNo,
                countryNo = readExcelRequest.countryNo,
                month = readExcelRequest.month,
                sku = data?.get("sku").toString(),
                fnsku = data?.get("fnsku").toString(),
                asin = data?.get("asin").toString(),
                productName = data?.get("productName").toString(),
                // TODO condition으로 하면 insert error 발생 일단 임시 조치
                productCondition = data?.get("productCondition").toString(),
                priceAmt = data?.get("priceAmt").toFloat(),
                mfnYn = data?.get("mfnYn").toNy(),
                mfnQty = data?.get("mfnQty").toInt(),
                afnYn = data?.get("afnYn").toNy(),
                afnWareQty = data?.get("afnWareQty").toInt(),
                afnQty = data?.get("afnQty").toInt(),
                afnUnsellQty = data?.get("afnUnsellQty").toInt(),
                afnReservedQty = data?.get("afnReservedQty").toInt(),
                afnTotalQty = data?.get("afnTotalQty").toInt(),
                perUnitVolume = data?.get("perUnitVolume").toFloat(),
                afnWorkQty = data?.get("afnWorkQty").toInt(),
                afnShipQty = data?.get("afnShipQty").toInt(),
                afnReceiveQty = data?.get("afnReceiveQty").toInt(),
                afnResearchQty = data?.get("afnResearchQty").toInt(),
                afnFutureReservedSupplyQty = data?.get("afnFutureReservedSupplyQty").toInt(),
                afnFutureBuySupplyQty = data?.get("afnFutureBuySupplyQty").toInt()
            )
        }

        stockService.createRawStock(Mapper.convertAll(entity))
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