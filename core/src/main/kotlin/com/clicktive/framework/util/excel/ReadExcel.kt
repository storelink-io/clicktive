package com.clicktive.framework.util.excel

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream

@Component
class ReadExcel {
    fun test(file: MultipartFile): String {
        val workbook: Workbook = XSSFWorkbook(file.inputStream)
        val sheet = workbook.getSheetAt(0)

        var result = ""

        for(row in sheet) {
            result += row.getCell(1).toString()
        }

        workbook.close()

        return result
    }
}