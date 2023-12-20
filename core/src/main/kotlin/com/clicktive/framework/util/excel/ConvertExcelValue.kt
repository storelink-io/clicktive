package com.clicktive.framework.util.excel

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

// TODO null 처리 어떻게 할 건지 확인 후 변경
object ConvertExcelValue {
    fun Any?.toString(): String {
        return this.takeIf { it != null }?.let { this as String } ?: ""
    }

    fun Any?.toInt(): Int {
        return this.takeIf { it != null }?.let { (this as Double).toInt() } ?: 0
    }

    fun Any?.toFloat(): Float {
        return this.takeIf { it != null }?.let { (this as Double).toFloat() } ?: 0.toFloat()
    }

    fun Any?.toDate(): String? {
        return this.takeIf { it != null }?.let {
            when (it::class.java) {
                Double.javaClass -> {
                    SimpleDateFormat("yyyy-MM-dd").format(Date((it as Double).toLong()))
                }
                String.javaClass -> {
                    val extractedDate = (it as String).replace(".", "").replace(" ", "")
                    val parsedDate = LocalDate.parse(extractedDate, DateTimeFormatter.ofPattern("yyMMdd"))
                    parsedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                }
                else -> {
                    null
                }
            }
        }
    }
}