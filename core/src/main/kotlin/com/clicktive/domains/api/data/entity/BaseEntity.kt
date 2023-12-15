package com.clicktive.domains.api.data.entity

import com.clicktive.framework.springframework.annotation.NoArg
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@NoArg
class BaseEntity: BaseCreateEntity() {
    @LastModifiedDate
    @Comment("수정 일시")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var modifyDt: LocalDateTime? = null //var modifyDt: LocalDateTime = LocalDateTime.now()

    @LastModifiedBy
    @Comment("수정자")
    var modifyMemberNo : Long? = null
}




