package com.clicktive.domains.api.service.excel

import com.clicktive.domains.api.constant.excel.CellDataConstants
import com.clicktive.domains.api.data.dto.excel.ReadExcelRequest
import com.clicktive.domains.api.data.entity.ad.RawSalesProduct
import com.clicktive.domains.api.data.entity.ad.RawSalesProductOrg
import com.clicktive.domains.api.data.enum.ad.ResultFileTypeCd
import com.clicktive.domains.api.service.ad.ResultFileService
import com.clicktive.domains.api.service.ad.SalesProductService
import com.clicktive.framework.util.Mapper
import com.clicktive.framework.util.excel.ConvertExcelValue.toFloat
import com.clicktive.framework.util.excel.ConvertExcelValue.toInt
import com.clicktive.framework.util.excel.ConvertExcelValue.toString
import com.clicktive.framework.util.excel.ReadExcel
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ReadRawSalesProductExcelService(
    private val readExcel: ReadExcel,
    private val resultFileService: ResultFileService,
    private val salesProductService: SalesProductService
) {
    fun readRawSalesProduct(
        file: MultipartFile,
        readExcelRequest: ReadExcelRequest
    ): Int {
        val workbook: Workbook = XSSFWorkbook(file.inputStream)
        val sheet = workbook.getSheetAt(0)

        val cellData =
            readExcel.setCellIndex(row = sheet.getRow(0), cellData = CellDataConstants.RawSalesProductCellData)
        val entityData = readExcel.getEntityData(sheet, cellData)

        val entity = entityData.mapNotNull { data ->
            RawSalesProductOrg(
                rawSalesProductOrgNo = null,
                brandNo = readExcelRequest.brandNo,
                countryNo = readExcelRequest.countryNo,
                month = readExcelRequest.month,
                asinUpper = data?.get("asinUpper").toString(),
                asin = data?.get("asin").toString(),
                productName = data?.get("productName").toString(),
                sku = data?.get("sku").toString(),
                sessionAppNum = data?.get("sessionAppNum").toInt(),
                sessionAppB2bNum = data?.get("sessionAppB2bNum").toInt(),
                sessionBrowserNum = data?.get("sessionBrowserNum").toInt(),
                sessionBrowserB2bNum = data?.get("sessionBrowserB2bNum").toInt(),
                sessionTotalNum = data?.get("sessionTotalNum").toInt(),
                sessionB2bTotalNum = data?.get("sessionB2bTotalNum").toInt(),
                sessionAppRate = data?.get("sessionAppRate").toFloat(),
                sessionAppB2bRate = data?.get("sessionAppB2bRate").toFloat(),
                sessionBrowserRate = data?.get("sessionBrowserRate").toFloat(),
                sessionBrowserB2bRate = data?.get("sessionBrowserB2bRate").toFloat(),
                sessionTotalRate = data?.get("sessionTotalRate").toFloat(),
                sessionB2bTotalRate = data?.get("sessionB2bTotalRate").toFloat(),
                pageViewAppNum = data?.get("pageViewAppNum").toInt(),
                pageViewAppB2bNum = data?.get("pageViewAppB2bNum").toInt(),
                pageViewBrowserNum = data?.get("pageViewBrowserNum").toInt(),
                pageViewBrowserB2bNum = data?.get("pageViewBrowserB2bNum").toInt(),
                pageViewTotalNum = data?.get("pageViewTotalNum").toInt(),
                pageViewB2bTotalNum = data?.get("pageViewB2bTotalNum").toInt(),
                pageViewAppRate = data?.get("pageViewAppRate").toFloat(),
                pageViewAppB2bRate = data?.get("pageViewAppB2bRate").toFloat(),
                pageViewBrowserRate = data?.get("pageViewBrowserRate").toFloat(),
                pageViewBrowserB2bRate = data?.get("pageViewBrowserB2bRate").toFloat(),
                pageViewTotalRate = data?.get("pageViewTotalRate").toFloat(),
                pageViewB2bTotalRate = data?.get("pageViewB2bTotalRate").toFloat(),
                offerMainRate = data?.get("offerMainRate").toFloat(),
                offerRecommendB2bRate = data?.get("offerRecommendB2bRate").toFloat(),
                orderQty = data?.get("orderQty").toInt(),
                orderB2bQty = data?.get("orderB2bQty").toInt(),
                productSessionRate = data?.get("productSessionRate").toFloat(),
                productSessionB2bRate = data?.get("productSessionB2bRate").toFloat(),
                salesAmt = data?.get("salesAmt").toInt(),
                salesB2bAmt = data?.get("salesB2bAmt").toInt(),
                orderItemTotalQty = data?.get("orderItemTotalQty").toInt(),
                orderItemB2bTotalQty = data?.get("orderItemB2bTotalQty").toInt(),
            )
        }

        val rawSalesProduct = entity.groupBy { it.asin }.map { (asin, data) ->
            val firstData = data.first()

            val orderQty = data.sumOf { it.orderQty }
            val orderB2bQty = data.sumOf { it.orderB2bQty }
            val productSessionRate = (firstData.sessionTotalNum.takeIf { it > 0 }?.let { orderQty.div(firstData.sessionTotalNum) } ?: 0).toFloat()
            val productSessionB2bRate = (firstData.sessionB2bTotalNum.takeIf { it > 0 }?.let { orderB2bQty.div(firstData.sessionB2bTotalNum) } ?: 0.0).toFloat()
            val salesAmt = data.sumOf { it.salesAmt }
            val salesB2bAmt = data.sumOf { it.salesB2bAmt }
            val orderItemTotalQty = data.sumOf { it.orderItemTotalQty }
            val orderItemB2bTotalQty = data.sumOf { it.orderItemB2bTotalQty }

            RawSalesProduct(
                rawSalesProductNo = null,
                brandNo = readExcelRequest.brandNo,
                countryNo = readExcelRequest.countryNo,
                month = readExcelRequest.month,
                asin = asin,
                productName = firstData.productName,
                sku = firstData.sku,
                sessionAppNum = firstData.sessionAppNum,
                sessionAppB2bNum = firstData.sessionAppB2bNum,
                sessionBrowserNum = firstData.sessionBrowserNum,
                sessionBrowserB2bNum = firstData.sessionBrowserB2bNum,
                sessionTotalNum = firstData.sessionTotalNum,
                sessionB2bTotalNum = firstData.sessionB2bTotalNum,
                sessionAppRate = firstData.sessionAppRate,
                sessionAppB2bRate = firstData.sessionAppB2bRate,
                sessionBrowserRate = firstData.sessionBrowserRate,
                sessionBrowserB2bRate = firstData.sessionBrowserB2bRate,
                sessionTotalRate = firstData.sessionTotalRate,
                sessionB2bTotalRate = firstData.sessionB2bTotalRate,
                pageViewAppNum = firstData.pageViewAppNum,
                pageViewAppB2bNum = firstData.pageViewAppB2bNum,
                pageViewBrowserNum = firstData.pageViewBrowserNum,
                pageViewBrowserB2bNum = firstData.pageViewBrowserB2bNum,
                pageViewTotalNum = firstData.pageViewTotalNum,
                pageViewB2bTotalNum = firstData.pageViewB2bTotalNum,
                pageViewAppRate = firstData.pageViewAppRate,
                pageViewAppB2bRate = firstData.pageViewAppB2bRate,
                pageViewBrowserRate = firstData.pageViewBrowserRate,
                pageViewBrowserB2bRate = firstData.pageViewBrowserB2bRate,
                pageViewTotalRate = firstData.pageViewTotalRate,
                pageViewB2bTotalRate = firstData.pageViewB2bTotalRate,
                offerMainRate = firstData.offerMainRate,
                offerRecommendB2bRate = firstData.offerRecommendB2bRate,
                orderQty = orderQty,
                // TODO 이거는 sum 아닌지?
                orderB2bQty = firstData.orderB2bQty,
                productSessionRate = productSessionRate,
                productSessionB2bRate = productSessionB2bRate,
                salesAmt = salesAmt,
                salesB2bAmt = salesB2bAmt,
                orderItemTotalQty = orderItemTotalQty,
                orderItemB2bTotalQty = orderItemB2bTotalQty
            )
        }

        salesProductService.createRawSalesProductOrg(Mapper.convertAll(entity))
        salesProductService.createRawSalesProduct(Mapper.convertAll(rawSalesProduct))
        val rowNum = entity.size

        resultFileService.createResultFile(
            brandNo = readExcelRequest.brandNo,
            countryNo = readExcelRequest.countryNo,
            month = readExcelRequest.month,
            resultFileTypeCd = ResultFileTypeCd.RAW_SALES_PRODUCT.detailCode,
            rowNum = rowNum
        )

        return rowNum
    }
}