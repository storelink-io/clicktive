package com.clicktive.domains.api.data.dto.country

import com.clicktive.domains.api.data.enum.country.CountryStateEnum
import com.clicktive.framework.springframework.annotation.NoArg

@NoArg
class CountryResponse (
    var countryNo: Long, //국가 번호
    var countryName: String, //국가 명
    var currencyName: String, //통화 명
    var currency: String, //통화
    var countryStateCd: CountryStateEnum, //국가 상태 코드
)
