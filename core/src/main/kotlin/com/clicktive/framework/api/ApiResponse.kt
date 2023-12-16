package com.clicktive.framework.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ApiResponse<T>(
    var data: Map<String, T?>,
    var status: Int          = 200,
    var messageCode: String  = "COMM-OK",
    var message: String      = "성공적으로 수행했습니다",
    var timeStamp: String    = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
) {
    var detailMessage: String? = null

    fun convertToJson(): String {
        val mapper = ObjectMapper().registerKotlinModule()
        return mapper.writeValueAsString(this)
    }
}

