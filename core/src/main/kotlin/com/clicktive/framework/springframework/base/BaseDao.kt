package com.clicktive.framework.springframework.base

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.annotation.PostConstruct
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader

data class QueryItem(val id: String, val description: String?, val query: List<String>)

@Component
open class BaseDao () {
    private final var daoName: String = "BaseDao"

    private var sqlLogger: Logger = LoggerFactory.getLogger("BaseDao")

    private val objectMapper = jacksonObjectMapper()

    @Value("\${app.config.dao-locations}")
    private val daoFilePath: String? = null

    @Value("\${app.config.show-query}")
    private val isPrintQuery: Boolean? = null

    private lateinit var queryList: List<QueryItem>

    constructor(daoName: String) : this() {
        daoName.also { this.daoName = it }
        LoggerFactory.getLogger(this.daoName).also { sqlLogger = it }
    }

    @PostConstruct
    private fun post() {
        readJson()
        sqlLogger.info("${this.daoName} is initialized.")
    }
    init {

    }

    private fun readJson() {
        val fileName = "$daoName.json"
        val jsonFilePath: String = "$daoFilePath$fileName"

        val resource = ClassPathResource(jsonFilePath)
        val br = BufferedReader(InputStreamReader(resource.inputStream))

        var readText: String? = ""
        val sb = StringBuilder()
        while (br.readLine().also { readText = it } != null) {
            sb.append(readText).append(System.lineSeparator())
        }

        val resultStr: String = sb.toString()
        queryList = objectMapper.readValue(resultStr, object : TypeReference<List<QueryItem>>() {})
    }

    protected fun getRawQuery(queryId: String): String? {
        var retQuery: String? = null
        for(queryItem in queryList!!) {
            if (queryId == queryItem.id) {
                retQuery = queryItem.query.joinToString(separator = "\n").replace("^", "")
            }
        }
        if (isPrintQuery == true) sqlLogger.info("$queryId : \n$retQuery")
        return retQuery
    }

    protected fun queryBuilder(queryId: String): StringBuilder {
        return StringBuilder(getRawQuery(queryId))
    }
}