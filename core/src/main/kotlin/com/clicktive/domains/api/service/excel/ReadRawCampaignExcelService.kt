package com.clicktive.domains.api.service.excel

import com.clicktive.domains.api.constant.excel.CellDataConstants
import com.clicktive.domains.api.data.dto.excel.ReadExcelRequest
import com.clicktive.domains.api.data.entity.ad.RawCampaign
import com.clicktive.domains.api.data.enum.ad.ResultFileTypeCd
import com.clicktive.domains.api.service.ad.CampaignService
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
class ReadRawCampaignExcelService(
    private val readExcel: ReadExcel,
    private val resultFileService: ResultFileService,
    private val campaignService: CampaignService
) {
    fun readRawCampaign(
        file: MultipartFile?,
        readExcelRequest: ReadExcelRequest
    ): Int {
//        val file_ = FileInputStream("/Users/jenni/Desktop/Clicktive_Raw/03_AD_campaign_Daily_raw.xlsx")
//        val workbook: Workbook = XSSFWorkbook(file_)
        val workbook: Workbook = XSSFWorkbook(file!!.inputStream)
        val sheet = workbook.getSheetAt(0)

        val cellData = readExcel.setCellIndex(row = sheet.getRow(0), cellData = CellDataConstants.RawCampaignCellData)
        val entityData = readExcel.getEntityData(sheet, cellData)

        val entity = entityData.mapNotNull { data ->
            RawCampaign(
                rawCampaignNo = null,
                brandNo = readExcelRequest.brandNo,
                countryNo = readExcelRequest.countryNo,
                month = readExcelRequest.month,
                campaignDate = data?.get("campaignDate").toDate(),
                campaignState = data?.get("campaignState").toString(),
                campaignName = data?.get("campaignName").toString(),
                campaignStatus = data?.get("campaignStatus").toString(),
                campaignType = data?.get("campaignType").toString(),
                targeting = data?.get("targeting").toString(),
                campaignBid = data?.get("campaignBid").toString(),
                beginDate = data?.get("beginDate").toDate(),
                endDate = data?.get("endDate").toDate(),
                portfolioName = data?.get("portfolioName").toString(),
                budgetAmt = data?.get("budgetAmt").toFloat(),
                searchUpperIs = data?.get("searchUpperIs").toString(),
                expenseType = data?.get("expenseType").toString(),
                viewNum = data?.get("viewNum").toInt(),
                clickNum = data?.get("clickNum").toInt(),
                ctr = data?.get("ctr").toInt(),
                expenseAmt = data?.get("expenseAmt").toFloat(),
                cpc = data?.get("cpc").toInt(),
                orderQty = data?.get("orderQty").toInt(),
                salesAmt = data?.get("salesAmt").toFloat(),
                acos = data?.get("acos").toInt(),
                roas = data?.get("roas").toInt(),
                newOrderQty = data?.get("newOrderQty").toInt(),
                newOrderRate = data?.get("newOrderRate").toFloat(),
                newSalesAmt = data?.get("newSalesAmt").toInt(),
                newSalesRate = data?.get("newSalesRate").toFloat(),
                exposureNum = data?.get("exposureNum").toInt(),
                vcpm = data?.get("vcpm").toInt(),
                video1Qrtr = data?.get("video1Qrtr").toFloat(),
                video2Qrtr = data?.get("video2Qrtr").toFloat(),
                video3Qrtr = data?.get("video3Qrtr").toFloat(),
                videoAllQrtr = data?.get("videoAllQrtr").toFloat(),
                muteCancel = data?.get("muteCancel").toFloat(),
                vtr = data?.get("vtr").toFloat(),
                vctr = data?.get("vctr").toFloat()
            )
        }

        campaignService.createRawCampaign(Mapper.convertAll(entity))
        val rowNum = entity.size

        resultFileService.createResultFile(
            brandNo = readExcelRequest.brandNo,
            countryNo = readExcelRequest.countryNo,
            month = readExcelRequest.month,
            resultFileTypeCd = ResultFileTypeCd.RAW_CAMPAIGN.detailCode,
            rowNum = rowNum
        )

        return rowNum
    }
}