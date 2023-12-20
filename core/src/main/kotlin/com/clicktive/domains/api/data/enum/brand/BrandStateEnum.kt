package com.clicktive.domains.api.data.enum.brand

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

//브랜드 상태 코드
enum class BrandStateEnum(val code: String) {
    정상("100"),
    삭제("400");

    companion object {
        fun fromCode(code: String): BrandStateEnum {
            return values().firstOrNull {
                it.code == code
            } ?: throw IllegalArgumentException("Format $code is illegal")
        }
    }
}

@Converter
class BrandStateEnumConverter : AttributeConverter<BrandStateEnum, String> {
    override fun convertToDatabaseColumn(attribute: BrandStateEnum?): String? {
        if (attribute == null) return null
        return attribute.code
    }
    override fun convertToEntityAttribute(code: String?): BrandStateEnum? {
        if (code == null) return null
        return BrandStateEnum.fromCode(code)
    }
}