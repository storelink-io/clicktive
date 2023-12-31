package com.clicktive.domains.api.repository.common

import com.clicktive.domains.api.data.entity.common.CodeDetail
import com.clicktive.domains.api.data.entity.common.CodeDetailPk
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CodeDetailRepository : JpaRepository<CodeDetail, CodeDetailPk> {
    @Query("select m from CodeDetail m where m.mainCode = :mainCode and m.useYn = 'Y' order by m.sortNo ")
    fun getByMainCode(mainCode: String): MutableList<CodeDetail>
}