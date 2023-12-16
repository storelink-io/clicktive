package com.clicktive.framework.springframework.base

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.clicktive.framework.api.ApiResponse
import com.clicktive.framework.util.TypeCheckUtils.isBasic
import com.clicktive.framework.util.TypeCheckUtils.isEmpty
import com.clicktive.framework.util.TypeCheckUtils.isList
import com.clicktive.framework.util.TypeCheckUtils.isMap
import com.clicktive.framework.util.TypeCheckUtils.isVoid
import kr.placeup.domains.pluapi.data.code.getCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Controller
class BaseController(
    val defaultExcludeProperties: MutableList<String> = mutableListOf("createDt", "createMemberNo", "modifyDt", "modifyMemberNo")
) {
    @Autowired
    lateinit var objectMapper: ObjectMapper

    fun <T> httpResponse (
        result: T? = null,
        excludeProperties: MutableList<String>? = defaultExcludeProperties
    ): ApiResponse<T> {
        val responseData = (if(result != null) {
            val resultStr = objectMapper.writeValueAsString(result)

            if (result.isEmpty() || result.isVoid() || result.isBasic()) result
            else if (result.isList()) {
                val returnList: MutableList<Map<String, Any?>> = mutableListOf()
                val resultListMap: List<Map<String, Any?>> =
                    objectMapper.readValue(resultStr, object : TypeReference<List<Map<String, Any?>>>() {})

                if (resultListMap.isNotEmpty()) {
                    for (resultMap in resultListMap) {
                        if (resultMap.isNotEmpty()) {
                            returnList.add(appendCodeNm(resultMap, excludeProperties))
                        }
                    }
                }
                returnList
            } else {
                var resultMap: MutableMap<String, Any?> = java.util.HashMap()
                resultMap = objectMapper.readValue(resultStr, (resultMap).javaClass)
                val returnMap = this.appendCodeNm(resultMap, excludeProperties)
                returnMap
            }
        } else {
            null
        }) as T

        return ApiResponse(
            data =  mapOf("content" to responseData),
            timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
        )
    }

    fun appendCodeNm(resultMap: Map<String, Any?>?, excludeProperties: MutableList<String>?): MutableMap<String, Any?> {
        val returnMap: MutableMap<String, Any?> = HashMap()

        if (resultMap != null) {
            for(result in resultMap) {
                if(result.value.isList()) {
                    // 리스트
                    val returnList: MutableList<Map<String, Any?>> = mutableListOf()
                    val resultList: List<Any?> = objectMapper.readValue(objectMapper.writeValueAsString(result.value), object : TypeReference<List<Any?>>() {})

                    returnMap[result.key] = if(!resultList.isNullOrEmpty()) {
                        if(resultList[0].isMap()) {
                            val resultListMap: List<Map<String, Any?>> = objectMapper.readValue(objectMapper.writeValueAsString(result.value), object : TypeReference<List<Map<String, Any?>>>() {})
                            if (!resultListMap.isNullOrEmpty()) {
                                for (resultMap in resultListMap) {
                                    if (!resultMap.isNullOrEmpty()) {
                                        val returnMap = appendCodeNm(resultMap, excludeProperties)
                                        returnList.add(returnMap)
                                    }
                                }
                            }

                            returnList
                        } else {
                            result.value
                        }
                    } else null
                } else if(result.value.isMap()) {
                    // 맵
                    returnMap[result.key] = appendCodeNm(objectMapper.readValue(objectMapper.writeValueAsString(result.value), (returnMap).javaClass), excludeProperties)
                } else {
                    if (!checkExclude(result.key, excludeProperties)) {
                        returnMap[result.key] = result.value
                        val key = result.key
                        val value: Any? = result.value
                        val keyType = key.substring(key.length - 2)
                        if(key.length > 2 && keyType == "Cd") {
                            if (value != null)
                                returnMap[key + "Name"] = getCode(key, value as String) ?: ""
                        }
                    }
                }
            }
        }

        return returnMap
    }

    fun checkExclude(nkey: String, excludeProperties: MutableList<String>?): Boolean {
        var ret = false
        if (excludeProperties != null ) {
            for (excludeProperty in excludeProperties) {
                if (nkey == excludeProperty) {
                    ret = true
                    break
                }
            }
        }

        return ret
    }
}