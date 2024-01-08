package com.clicktive.domains.api.data.dto.brand

import com.clicktive.framework.springframework.annotation.NoArg
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

@NoArg
class BrandResponse (
    var brandNo: Long, //브랜드 번호
    var companyNo: Long, //회사 번호
    var brandName: String, //브랜드 명
    var brandStateCd: String, //브랜드 상태
    var createDt: LocalDateTime
)