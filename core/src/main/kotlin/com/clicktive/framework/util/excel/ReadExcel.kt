package com.clicktive.framework.util.excel

import com.clicktive.framework.exception.ServiceException
import org.apache.poi.ss.usermodel.*
import org.springframework.stereotype.Component

@Component
class ReadExcel {
    fun setCellIndex(
        row: Row,
        cellData: List<CellData>
    ): List<CellData> {
        for (cell in cellData) {
            cell.cellIndex = row.singleOrNull { it.stringCellValue in cell.cellName }?.columnIndex
        }

        return cellData
    }

    fun getEntityData(
        sheet: Sheet,
        cellData: List<CellData>
    ): List<Map<String, Any?>> {
        val exceptionsMessageParams = mutableListOf<String>()

        val entity = sheet.filter { row -> row.rowNum > 0 }.map { row ->
            val entityData = mutableMapOf<String, Any?>()

            cellData.forEach { cell ->
                val data = cell.cellIndex?.let { cellnum -> row.getCell(cellnum) }

                // TODO 데이터 예외 처리 어떻게 할 건지 확인 필요
                when {
                    data?.cellType == cell.cellType -> {
                        val value = getValue(cell, data)
                        entityData[cell.columnName] = value
                    }
                    cell.isRequired && data?.cellType != cell.cellType -> {
                        exceptionsMessageParams.add("${row.rowNum?.plus(1)}: ${cell.cellName.first()}")
                    }
                }
            }

            entityData
        }

        // TODO Exception 발생 시 error 발생
        exceptionsMessageParams.takeIf { it.isNotEmpty() }?.let {
            throw ServiceException(
                messageCode = "ERR-009",
                messageParams = arrayOf(exceptionsMessageParams.joinToString(separator = ", "))
            )
        }

        return entity
    }

    private fun getValue(cell: CellData, data: Cell?): Any? {
        return when (cell.cellType) {
            CellType.STRING -> {
                data?.stringCellValue
            }
            CellType.NUMERIC -> {
                data?.numericCellValue
            }
            else -> {
                null
            }
        }
    }
}