package com.clicktive.domains.api.data.dto.brand

import com.clicktive.domains.api.data.enum.brand.BrandStateEnum
import com.clicktive.framework.springframework.annotation.NoArg

@NoArg
class BrandRequest (
    var brandNo: Long?, //브랜드 번호
    var companyNo: Long, //회사 번호
    var brandName: String, //브랜드 명
    var brandStateCd: BrandStateEnum, //브랜드 상태 코드
    var countries: MutableList<Long>, //국가 리스트
)

@NoArg
class BrandMemberRequest (
    var brandNo: Long, //브랜드 번호
    var members: MutableList<Long>, //회원 리스트
)

@NoArg
class SearchBrandRequest (
    var brandNo: Long?, //브랜드 번호
    var companyNo: Long?, //회사 번호
    var brandName: String?, //브랜드 명
    var brandStateCd: String?, //브랜드 상태 코드
)