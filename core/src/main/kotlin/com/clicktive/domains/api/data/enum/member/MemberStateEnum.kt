package com.clicktive.domains.api.data.enum.member

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter


//회원 상태 코드
enum class MemberStateEnum(val code: String) {
    정상("100"),
    정지("200"),
    휴면("300"),
    탈퇴("400");

    companion object {
        fun fromCode(code: String): MemberStateEnum {
            return values().firstOrNull {
                it.code == code
            } ?: throw IllegalArgumentException("Format $code is illegal")
        }
    }
}

@Converter
class MemberStateEnumConverter : AttributeConverter<MemberStateEnum, String> {
    override fun convertToDatabaseColumn(attribute: MemberStateEnum?): String? {
        if (attribute == null) return null
        return attribute.code
    }
    override fun convertToEntityAttribute(code: String?): MemberStateEnum? {
        if (code == null) return null
        return MemberStateEnum.fromCode(code)
    }
}