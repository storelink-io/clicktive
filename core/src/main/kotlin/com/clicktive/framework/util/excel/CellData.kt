package com.clicktive.framework.util.excel

import org.apache.poi.ss.usermodel.CellType

data class CellData(
    val cellName: List<String>,
    val cellType: CellType,
    val isRequired: Boolean,
    val columnName: String,
    var cellIndex: Int? = null
)