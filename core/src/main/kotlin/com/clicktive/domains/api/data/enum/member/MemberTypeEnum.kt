package com.clicktive.domains.api.data.enum.member

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

//회원 유형 코드
enum class MemberTypeEnum(val code: String) {
    일반회원("100"),
    관리자("900");

    companion object {
        fun fromCode(code: String): MemberTypeEnum {
            return values().firstOrNull {
                it.code == code
            } ?: throw IllegalArgumentException("Format $code is illegal")
        }
    }
}

@Converter
class MemberTypeEnumConverter : AttributeConverter<MemberTypeEnum, String> {
    override fun convertToDatabaseColumn(attribute: MemberTypeEnum?): String? {
        if (attribute == null) return null
        return attribute.code
    }
    override fun convertToEntityAttribute(code: String?): MemberTypeEnum? {
        if (code == null) return null
        return MemberTypeEnum.fromCode(code)
    }
}