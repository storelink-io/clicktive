package com.clicktive.domains.api.service.excel

import com.clicktive.domains.api.constant.excel.CellDataConstants
import com.clicktive.domains.api.data.dto.excel.ReadExcelRequest
import com.clicktive.domains.api.data.entity.ad.RawCampaignKeyword
import com.clicktive.domains.api.data.entity.ad.RawCampaignKeywordOrg
import com.clicktive.domains.api.data.enum.ad.ResultFileTypeCd
import com.clicktive.domains.api.service.ad.CampaignKeywordService
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
class ReadRawCampaignKeywordExcelService(
    private val readExcel: ReadExcel,
    private val resultFileService: ResultFileService,
    private val campaignKeywordService: CampaignKeywordService
) {
    fun readRawCampaignKeyword(
        file: MultipartFile?,
        readExcelRequest: ReadExcelRequest
    ): Int {
//        val file_ = FileInputStream("/Users/jenni/Desktop/Clicktive_Raw/05_keyword_RAW.xlsx")
//        val workbook: Workbook = XSSFWorkbook(file_)
        val workbook: Workbook = XSSFWorkbook(file!!.inputStream)
        val sheet = workbook.getSheetAt(0)

        val cellData =
            readExcel.setCellIndex(row = sheet.getRow(0), cellData = CellDataConstants.RawCampaignKeywordCellData)
        val entityData = readExcel.getEntityData(sheet, cellData)

        val entity = entityData.mapNotNull { data ->
            RawCampaignKeywordOrg(
                rawAdKeywordOrgNo = null,
                brandNo = readExcelRequest.brandNo,
                countryNo = readExcelRequest.countryNo,
                month = readExcelRequest.month,
                startDate = data?.get("startDate").toDate(),
                endDate = data?.get("endDate").toDate(),
                portfolioName = data?.get("portfolioName").toString(),
                currency = data?.get("currency").toString(),
                campaignName = data?.get("campaignName").toString(),
                campaignGroup = data?.get("campaignGroup").toString(),
                keyword = data?.get("keyword").toString(),
                accordanceType = data?.get("accordanceType").toString(),
                keywordCustomer = data?.get("keywordCustomer").toString(),
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

        val rawCampaignKeyword = entity.groupBy { it.keyword }.map { (keyword, data) ->
            val firstData = data.first()

            val viewNum = data.sumOf { it.viewNum }
            val clickNum = data.sumOf { it.clickNum }
            val salesTotal7Amt = data.map { it.salesTotal7Amt }.sum()
            val expenseAmt = data.map { it.expenseAmt }.sum()
            val orderToal7Num = data.sumOf { it.orderToal7Num }
            val orderTotal7Qty = data.sumOf { it.orderTotal7Qty }
            val adSku7Num = data.sumOf { it.adSku7Num }
            val etcSku7Num = data.sumOf { it.etcSku7Num }
            val adSkuSales7Amt = data.map { it.adSkuSales7Amt }.sum()
            val etcSkuSales7Amt = data.map { it.etcSkuSales7Amt }.sum()

            val ctr = (clickNum.takeIf { it > 0 }?.let { salesTotal7Amt.div(clickNum) } ?: 0).toFloat()
            val cpc = (clickNum.takeIf { it > 0 }?.let { expenseAmt.div(clickNum) } ?: 0).toFloat()
            val acos = (salesTotal7Amt.takeIf { it > 0 }?.let { (expenseAmt.times(100)).div(salesTotal7Amt.times(100)) } ?: 0).toFloat()
            val roas = salesTotal7Amt.div(expenseAmt).times(100)
            val conversion7Rate = (clickNum.takeIf { it > 0 }?.let { orderToal7Num.div(clickNum) } ?: 0).toFloat()

            RawCampaignKeyword(
                rawAdKeywordNo = null,
                brandNo = readExcelRequest.brandNo.toInt(),
                countryNo = readExcelRequest.countryNo.toInt(),
                month = readExcelRequest.month,
                // TODO 미사용 데이터 확인 필요
                startDate = firstData.startDate,
                endDate = firstData.endDate,
                portfolioName = firstData.portfolioName,
                currency = firstData.currency,
                campaignName = firstData.campaignName,
                campaignGroup = firstData.campaignGroup,
                // TODO keyword 저장할 필요 없는지? 저장해야 할듯~
                keyword = firstData.keyword,
                sku = null,
                asin = null,
                // TODO 미사용 데이터 확인 필요
                viewNum = viewNum,
                clickNum = clickNum,
                ctr = ctr,
                cpc = cpc,
                expenseAmt = expenseAmt,
                salesTotal7Amt = salesTotal7Amt,
                acos = acos,
                roas = roas,
                orderToal7Num = orderToal7Num,
                orderTotal7Qty = orderTotal7Qty,
                conversion7Rate = conversion7Rate,
                adSku7Num = adSku7Num,
                etcSku7Num = etcSku7Num,
                adSkuSales7Amt = adSkuSales7Amt,
                etcSkuSales7Amt = etcSkuSales7Amt
            )
        }

        campaignKeywordService.createRawCampaignKeywordOrg(Mapper.convertAll(entity))
        campaignKeywordService.createRawCampaignKeyword(Mapper.convertAll(rawCampaignKeyword))
        val rowNum = entity.size

        resultFileService.createResultFile(
            brandNo = readExcelRequest.brandNo,
            countryNo = readExcelRequest.countryNo,
            month = readExcelRequest.month,
            resultFileTypeCd = ResultFileTypeCd.RAW_CAMPAIGN_KEYWORD.detailCode,
            rowNum = rowNum
        )

        return rowNum
    }
}