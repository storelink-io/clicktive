package com.clicktive.domains.api.service.brand

import com.clicktive.domains.api.data.dto.brand.BrandMemberRequest
import com.clicktive.domains.api.data.dto.brand.BrandRequest
import com.clicktive.domains.api.data.entity.member.*
import com.clicktive.domains.api.repository.member.BrandCountryRepository
import com.clicktive.domains.api.repository.member.BrandMemberRepository
import com.clicktive.domains.api.repository.member.BrandRepository
import com.clicktive.framework.exception.ServiceException
import com.clicktive.framework.util.Mapper
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BrandService (
    private val brandRepository: BrandRepository,
    private val brandMemberRepository: BrandMemberRepository,
    private val brandCountryRepository: BrandCountryRepository
) {
    @Transactional
    fun saveBrand(request: BrandRequest, memberNo: Long) {
        lateinit var brand: Brand
        if (request.brandNo == null) {
            brand = Mapper.convert(request)
            brand.brandStateCd   = request.brandStateCd.code
            brand.createMemberNo = memberNo
            brand.modifyDt       = brand.createDt
            brand.modifyMemberNo = memberNo
        } else {
            //brand = brandRepository.getReferenceById(request.brandNo!!)
            brand = brandRepository.getBrand(request.brandNo!!) ?: throw ServiceException("BRD-001")

            brand.companyNo      = request.companyNo
            brand.brandName      = request.brandName
            brand.brandStateCd   = request.brandStateCd.code
            brand.modifyDt       = LocalDateTime.now()
            brand.modifyMemberNo = memberNo
        }
        brandRepository.save(brand)

        //브랜드 - 국가
        if (request.countries.size >= 1) {
            for (tempCountryNo in request.countries) {
                var brandCountry: BrandCountry? = brandCountryRepository.getBrandCountry(brand.brandNo!!, tempCountryNo)
                if (brandCountry == null) {
                    brandCountry = BrandCountry(
                        brand.brandNo!!,
                        tempCountryNo
                    )
                    brandCountry.createMemberNo = memberNo
                    brandCountryRepository.save(brandCountry)
                }
            }
        }
    }

    @Transactional
    fun saveBrandMember(request: BrandMemberRequest, memberNo: Long) {
        val brand = brandRepository.getBrand(request.brandNo) ?: throw ServiceException("BRD-001")

        //브랜드 - 회원
        if (request.members.size >= 1) {
            for (tempMemberNo in request.members) {
                var brandMember: BrandMember? = brandMemberRepository.getBrandMember(brand.brandNo!!, tempMemberNo)
                if (brandMember == null) {
                    brandMember = BrandMember(
                        brand.brandNo!!,
                        tempMemberNo
                    )
                    brandMember.createMemberNo = memberNo
                    brandMemberRepository.save(brandMember)
                }
            }
        }
    }
}