package com.clicktive.domains.api.data.enum.company

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

//회사 상태 코드
enum class CompanyStateEnum(val code: String) {
    정상("100"),
    삭제("400");

    companion object {
        fun fromCode(code: String): CompanyStateEnum {
            return values().firstOrNull {
                it.code == code
            } ?: throw IllegalArgumentException("Format $code is illegal")
        }
    }
}

@Converter
class CompanyStateEnumConverter : AttributeConverter<CompanyStateEnum, String> {
    override fun convertToDatabaseColumn(attribute: CompanyStateEnum?): String? {
        if (attribute == null) return null
        return attribute.code
    }
    override fun convertToEntityAttribute(code: String?): CompanyStateEnum? {
        if (code == null) return null
        return CompanyStateEnum.fromCode(code)
    }
}