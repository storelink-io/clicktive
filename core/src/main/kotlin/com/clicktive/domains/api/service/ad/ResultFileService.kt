package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.ResultFile
import com.clicktive.domains.api.repository.ad.ResultFileRepository
import com.clicktive.framework.util.Mapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ResultFileService(
    private val resultFileRepository: ResultFileRepository
) {
    @Transactional
    fun createResultFile(
        brandNo: Long,
        countryNo: Long,
        month: String,
        resultFileTypeCd: String,
        rowNum: Int
    ): ResultFile {
        deleteResultFile(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            resultFileTypeCd = resultFileTypeCd
        )

        val resultFile = ResultFile(
            resultFileNo = null,
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            resultFileTypeCd = resultFileTypeCd,
            rowNum = rowNum,
            registerYn = "N"
        )

        return resultFileRepository.save(Mapper.convert(resultFile))
    }

    fun deleteResultFile(
        brandNo: Long,
        countryNo: Long,
        month: String,
        resultFileTypeCd: String
    ) {
        resultFileRepository.deleteAllByBrandNoAndCountryNoAndMonthAndResultFileTypeCd(
            brandNo = brandNo,
            countryNo = countryNo,
            month = month,
            resultFileTypeCd = resultFileTypeCd
        )
    }
}