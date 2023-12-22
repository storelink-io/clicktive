package com.clicktive.domains.api.service.excel

import com.clicktive.domains.api.constant.excel.CellDataConstants
import com.clicktive.domains.api.data.dto.excel.ReadExcelRequest
import com.clicktive.domains.api.data.entity.ad.RawCampaignProduct
import com.clicktive.domains.api.data.enum.ad.ResultFileTypeCd
import com.clicktive.domains.api.service.ad.CampaignProductService
import com.clicktive.domains.api.service.ad.ResultFileService
import com.clicktive.framework.util.Mapper
import com.clicktive.framework.util.excel.ConvertExcelValue.toDate
import com.clicktive.framework.util.excel.ConvertExcelValue.toFloat
import com.clicktive.framework.util.excel.ConvertExcelValue.toInt
import com.clicktive.framework.util.excel.ConvertExcelValue.toString
import com.clicktive.framework.util.excel.ReadExcel
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream

@Service
class ReadRawCampaignProductExcelService(
    private val readExcel: ReadExcel,
    private val resultFileService: ResultFileService,
    private val campaignProductService: CampaignProductService
) {
    fun readRawCampaignProduct(
        file: MultipartFile?,
        readExcelRequest: ReadExcelRequest
    ): Int {
//        val file_ = FileInputStream("/Users/jenni/Desktop/Clicktive_Raw/04_AD_Product_raw.xlsx")
//        val workbook: Workbook = XSSFWorkbook(file_)
        val workbook: Workbook = XSSFWorkbook(file!!.inputStream)
        val sheet = workbook.getSheetAt(0)

        val cellData =
            readExcel.setCellIndex(row = sheet.getRow(0), cellData = CellDataConstants.RawCampaignProductCellData)
        val entityData = readExcel.getEntityData(sheet, cellData)

        val entity = entityData.mapNotNull { data ->
            RawCampaignProduct(
                rawAdProductNo = null,
                brandNo = readExcelRequest.brandNo,
                countryNo = readExcelRequest.countryNo,
                month = readExcelRequest.month,
                startDate = data?.get("startDate").toDate(),
                endDate = data?.get("endDate").toDate(),
                portfolioName = data?.get("portfolioName").toString(),
                currency = data?.get("currency").toString(),
                campaignName = data?.get("campaignName").toString(),
                campaignGroup = data?.get("campaignGroup").toString(),
                sku = data?.get("sku").toString(),
                asin = data?.get("asin").toString(),
                viewNum = data?.get("viewNum").toInt(),
                clickNum = data?.get("clickNum").toInt(),
                ctr = data?.get("ctr").toFloat(),
                cpc = data?.get("cpc").toFloat(),
                expenseAmt = data?.get("expenseAmt").toFloat(),
                salesTotal7Amt = data?.get("salesTotal7Amt").toFloat(),
                acos = data?.get("acos").toFloat(),
                roas = data?.get("roas").toFloat(),
                orderToal7Num = data?.get("orderToal7Num").toInt(),
                orderTotal7Qty = data?.get("orderTotal7Qty").toInt(),
                conversion7Rate = data?.get("conversion7Rate").toFloat(),
                adSku7Num = data?.get("adSku7Num").toInt(),
                etcSku7Num = data?.get("etcSku7Num").toInt(),
                adSkuSales7Amt = data?.get("adSkuSales7Amt").toFloat(),
                etcSkuSales7Amt = data?.get("etcSkuSales7Amt").toFloat()
            )
        }

        campaignProductService.createRawCampaignProduct(Mapper.convertAll(entity))
        val rowNum = entity.size

        resultFileService.createResultFile(
            brandNo = readExcelRequest.brandNo,
            countryNo = readExcelRequest.countryNo,
            month = readExcelRequest.month,
            resultFileTypeCd = ResultFileTypeCd.RAW_CAMPAIGN_PRODUCT.detailCode,
            rowNum = rowNum
        )

        return rowNum
    }
}