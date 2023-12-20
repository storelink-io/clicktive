package com.clicktive.domains.api.repository.member

import com.clicktive.domains.api.data.entity.member.Country
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CountryRepository : JpaRepository<Country, Long> {
    @Query("select m from Country m where m.countryStateCd = :countryStateCd ")
    fun findAllByCountryStateCd(countryStateCd: String = "100"): MutableList<Country>
}