package com.clicktive.domains.api.service.common

import com.clicktive.domains.api.data.dto.common.CodeDetailDto
import com.clicktive.domains.api.repository.common.CodeDetailRepository
import jakarta.annotation.PostConstruct
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class CodeMappingService(
    val codeDetailRepository: CodeDetailRepository
) {
    private val log = KotlinLogging.logger { }

    @Value("\${service.name}")
    val serviceName = ""

    @PostConstruct
    fun init() {
        if (serviceName == "clicktive") {
            codes =
                runBlocking {
                    codeDetailRepository.findAll().toList().map {
                        CodeDetailDto(
                            mainCode = it.mainCode,
                            detailCode = it.detailCode,
                            detailCodeName = it.detailCodeName,
                            codeDesc1 = it.codeDesc1,
                            codeDesc2 = it.codeDesc2,
                            codeDesc3 = it.codeDesc3,
                            sortNo = it.sortNo,
                            useYn = it.useYn
                        )
                    }
                }

            codes.forEach {
                codeMap[Pair(it.mainCode, it.detailCode)] = it
            }
            log.info { "@@ CodeMappingService code initialized @@" }
        }
    }

    companion object {
        private var codes: List<CodeDetailDto> = listOf()
        private var codeMap: HashMap<Pair<String, String>, CodeDetailDto> = hashMapOf()

        @Suppress("unused")
        fun getAllCodes(): List<CodeDetailDto> {
            return codes
        }

        fun findAllByMainCode(mainCode: String): List<CodeDetailDto> {
            return codes.filter { it.mainCode == mainCode }
        }

        fun findByMainCodeAndDetailCode(mainCode: String, detailCode: String): CodeDetailDto? {
            return codeMap[Pair(mainCode, detailCode)]
        }
    }
}