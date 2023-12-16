package com.clicktive.domains.api.data.entity.common

import com.clicktive.domains.api.data.entity.BaseEntity
import com.clicktive.framework.springframework.annotation.NoArg
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "ctv_cm_code_bsc")
@NoArg
class CodeMain (
    @Id
    @org.springframework.data.annotation.Id
    @Comment("메인 코드")
    var mainCode: String,

    @Comment("메인 코드 이름")
    var mainCodeName: String,

    @Comment("사용 여부")
    var useYn: String?,

) : BaseEntity() {
}
