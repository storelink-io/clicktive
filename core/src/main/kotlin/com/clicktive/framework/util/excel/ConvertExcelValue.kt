package com.clicktive.framework.util.excel

object ConvertExcelValue {
    fun Any?.toString(): String {
        return this as String
    }

    fun Any?.toInt(): Int {
        return (this as Double).toInt()
    }

    fun Any?.toFloat(): Float {
        return (this as Double).toFloat()
    }
}