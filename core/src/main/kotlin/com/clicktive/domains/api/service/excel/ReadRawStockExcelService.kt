package com.clicktive.domains.api.service.excel

import com.clicktive.domains.api.constant.excel.CellDataConstants
import com.clicktive.domains.api.data.dto.excel.ReadExcelRequest
import com.clicktive.domains.api.data.enum.ad.ResultFileTypeCd
import com.clicktive.domains.api.service.ad.ResultFileService
import com.clicktive.framework.util.excel.ReadExcel
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ReadRawStockExcelService(
    private val readExcel: ReadExcel,
    private val resultFileService: ResultFileService
) {
    fun readRawStock(
        file: MultipartFile,
        readExcelRequest: ReadExcelRequest
    ): Int {
        val workbook: Workbook = XSSFWorkbook(file.inputStream)
        val sheet = workbook.getSheetAt(0)

        val cellData = readExcel.setCellIndex(row = sheet.getRow(0), cellData = CellDataConstants.RawStockCellData)
        val entityData = readExcel.getEntityData(sheet, cellData)

        val entity = entityData.mapNotNull { data ->

        }

        resultFileService.createResultFile(
            brandNo = readExcelRequest.brandNo,
            countryNo = readExcelRequest.countryNo,
            month = readExcelRequest.month,
            resultFileTypeCd = ResultFileTypeCd.RAW_SALES.detailCode,
            rowNum = 0
        )

        return 0
    }
}