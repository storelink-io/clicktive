package com.clicktive.domains.api.data.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import com.clicktive.framework.springframework.annotation.NoArg
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@NoArg
abstract class BaseCreateEntity {
    @CreatedDate
    @Comment("생성 일시")
//    @Column(name =  "createDt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createDt: LocalDateTime = LocalDateTime.now()

    @CreatedBy
    @Comment("생성자")
//    @Column(name =  "createMemberNo")
    var createMemberNo : Long? = null
}




