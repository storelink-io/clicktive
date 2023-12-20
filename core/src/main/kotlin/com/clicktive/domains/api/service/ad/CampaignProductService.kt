package com.clicktive.domains.api.service.ad

import com.clicktive.domains.api.data.entity.ad.RawCampaignProduct
import com.clicktive.domains.api.repository.ad.RawCampaignProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CampaignProductService(
    private val rawCampaignProductRepository: RawCampaignProductRepository
) {
    @Transactional
    fun createRawCampaignProduct(rawCampaignProduct: List<RawCampaignProduct>): List<RawCampaignProduct> {
        deleteRawCampaignProduct(
            brandNo = rawCampaignProduct.first().brandNo,
            countryNo = rawCampaignProduct.first().countryNo,
            month = rawCampaignProduct.first().month
        )
        return rawCampaignProductRepository.saveAll(rawCampaignProduct)
    }

    fun deleteRawCampaignProduct(
        brandNo: Long,
        countryNo: Long,
        month: String
    ) {
        rawCampaignProductRepository.deleteAllByBrandNoAndCountryNoAndMonth(brandNo = brandNo, countryNo = countryNo, month = month)
    }
}