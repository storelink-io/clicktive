package com.clicktive.domains.api.service.company

import com.clicktive.domains.api.data.dto.company.CompanyRequest
import com.clicktive.domains.api.data.entity.member.Company
import com.clicktive.domains.api.repository.member.CompanyRepository
import com.clicktive.framework.util.Mapper
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CompanyService(
    private val companyRepository: CompanyRepository
) {
    @Transactional
    fun saveCompany(request: CompanyRequest, memberNo: Long) {
        lateinit var company: Company
        if (request.companyNo == null) {
            company = Mapper.convert(request)
            company.companyStateCd = request.companyStateCd.code
            company.createMemberNo = memberNo
            company.modifyDt       = company.createDt
            company.modifyMemberNo = memberNo
        } else {
            company = companyRepository.getReferenceById(request.companyNo)
            company.companyName    = request.companyName
            company.ceoName        = request.ceoName
            company.bizNumber      = request.bizNumber
            company.companyStateCd = request.companyStateCd.code
            company.address        = request.address
            company.modifyDt       = LocalDateTime.now()
            company.modifyMemberNo = memberNo
        }
        companyRepository.save(company)
    }
}