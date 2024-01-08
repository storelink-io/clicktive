package com.clicktive.domains.api.data.dto.company

import com.clicktive.domains.api.data.entity.member.Company
import com.clicktive.domains.api.data.enum.company.CompanyStateEnum
import com.clicktive.framework.springframework.annotation.NoArg

@NoArg
data class CompanyResponse (
    val companyNo: Long, //회사 번호
    val companyName: String?, //회사명
    val companyStateCd: String, //회사 상태 코드
    val bizNumber: String?, //사업등록번호
    val ceoName: String?, //대표자명
    val address: String?
)