package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.RawSalesProduct
import com.clicktive.domains.api.data.entity.ad.RawSalesProductOrg
import com.clicktive.domains.api.repository.ad.RawSalesProductOrgRepository
import com.clicktive.domains.api.repository.ad.RawSalesProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SalesProductService(
    private val rawSalesProductOrgRepository: RawSalesProductOrgRepository,
    private val rawSalesProductRepository: RawSalesProductRepository
) {
    @Transactional
    fun createRawSalesProductOrg(rawSalesProductOrg: List<RawSalesProductOrg>): List<RawSalesProductOrg> {
        deleteRawSalesProductOrg(
            brandNo = rawSalesProductOrg.first().brandNo,
            countryNo = rawSalesProductOrg.first().countryNo,
            month = rawSalesProductOrg.first().month
        )
        return rawSalesProductOrgRepository.saveAll(rawSalesProductOrg)
    }

    @Transactional
    fun createRawSalesProduct(rawSalesProduct: List<RawSalesProduct>): List<RawSalesProduct> {
        deleteRawSalesProduct(
            brandNo = rawSalesProduct.first().brandNo,
            countryNo = rawSalesProduct.first().countryNo,
            month = rawSalesProduct.first().month
        )
        return rawSalesProductRepository.saveAll(rawSalesProduct)
    }

    fun deleteRawSalesProductOrg(
        brandNo: Long,
        countryNo: Long,
        month: String
    ) {
        rawSalesProductOrgRepository.deleteAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }

    fun deleteRawSalesProduct(
        brandNo: Long,
        countryNo: Long,
        month: String
    ) {
        rawSalesProductRepository.deleteAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}