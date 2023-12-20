package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.code.resultFileTypeCd
import com.clicktive.domains.api.data.dto.ad.ResultFileRespone
import com.clicktive.domains.api.repository.ad.ResultFileRepository
import org.springframework.stereotype.Service

@Service
class GetResultFileService(
    private val resultFileRepository: ResultFileRepository
) {
    fun getResultFileRespone(
        brandNo: Long,
        countryNo: Long,
        month: String
    ): List<ResultFileRespone> {
        val resultFile = resultFileRepository.findAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)

        return resultFileTypeCd.map { (detailCode, detailCodeName) ->
            val file = resultFile.singleOrNull { it.resultFileTypeCd == detailCode }

            ResultFileRespone(
                resultFileTypeCd = detailCode,
                resultFileTypeCdName = detailCodeName,
                isUploaded = (file != null)
            )
        }
    }
}