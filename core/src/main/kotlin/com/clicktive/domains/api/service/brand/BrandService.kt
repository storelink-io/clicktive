package com.clicktive.domains.api.service.brand

import com.clicktive.domains.api.data.dto.brand.BrandRequest
import com.clicktive.domains.api.data.entity.member.Brand
import com.clicktive.domains.api.repository.member.BrandRepository
import com.clicktive.framework.util.Mapper
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BrandService (
    private val brandRepository: BrandRepository
) {
    @Transactional
    fun saveBrand(request: BrandRequest, memberNo: Long) {
        lateinit var brand: Brand
        if (request.brandNo == null) {
            brand = Mapper.convert(request)
            brand.createMemberNo = memberNo
            brand.modifyDt       = brand.createDt
            brand.modifyMemberNo = memberNo
        } else {
            brand = brandRepository.getReferenceById(request.brandNo!!)
            brand.brandName      = request.brandName
            brand.brandStateCd   = request.brandStateCd.code
            brand.modifyDt       = LocalDateTime.now()
            brand.modifyMemberNo = memberNo
        }
        brandRepository.save(brand)
    }
}