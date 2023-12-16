package com.clicktive.domains.api.data.entity.common

import com.clicktive.domains.api.data.entity.BaseEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.io.Serializable

@NoArg
data class CodeDetailPk(
    var mainCode: String,
    var detailCode: String
) : Serializable

@Entity
@Table(name = "ctv_cm_code_dtl")
@IdClass(CodeDetailPk::class)
@NoArg
class CodeDetail (
    @Id
    @org.springframework.data.annotation.Id
    @Comment("메인 코드")
    var mainCode: String,

    @Id
    @org.springframework.data.annotation.Id
    @Comment("상세 코드")
    var detailCode: String,

    @Comment("상세 코드 이름")
    var detailCodeName: String,

    @Comment("코드 설명 1")
    var codeDesc1: String?,

    @Comment("코드 설명 2")
    var codeDesc2: String?,

    @Comment("코드 설명 3")
    var codeDesc3: String?,

    @Comment("노출 순번")
    var sortNo: Int,

    @Comment("사용 여부")
    var useYn: String?,

) : BaseEntity() {
}
