package com.clicktive.domains.api.service.country

import com.clicktive.domains.api.data.dto.country.CountryRequest
import com.clicktive.domains.api.data.entity.member.Country
import com.clicktive.domains.api.repository.member.CountryRepository
import com.clicktive.framework.util.Mapper
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CountryService (
  private val countryRepository: CountryRepository
) {
    @Transactional
    fun saveCountry(request: CountryRequest, memberNo: Long) {
        lateinit var country: Country
        if (request.countryNo == null) {
            country = Mapper.convert(request)
            country.countryStateCd = request.countryStateCd.code
            country.createMemberNo = memberNo
            country.modifyDt       = country.createDt
            country.modifyMemberNo = memberNo
        } else {
            country = countryRepository.getReferenceById(request.countryNo!!)
            country.countryName    = request.countryName
            country.currencyName   = request.currencyName
            country.currency       = request.currency
            country.countryStateCd = request.countryStateCd.code
            country.modifyDt       = LocalDateTime.now()
            country.modifyMemberNo = memberNo
        }
        countryRepository.save(country)
    }
}