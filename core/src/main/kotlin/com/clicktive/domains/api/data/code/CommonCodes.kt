package kr.placeup.domains.pluapi.data.code

fun getCode(masterCode: String, detailCode: String?): String? {
    var value: String? = null

    if (detailCode != null) {
        when (masterCode) {
            "brandStateCd" -> {
                value = brandStateCd[detailCode]
            }
            "campaignTypeCd" -> {
                value = campaignTypeCd[detailCode]
            }
            "companyStateCd" -> {
                value = companyStateCd[detailCode]
            }
            "countryStateCd" -> {
                value = countryStateCd[detailCode]
            }
            "memberStateCd" -> {
                value = memberStateCd[detailCode]
            }
            "memberTypeCd" -> {
                value = memberTypeCd[detailCode]
            }
            "resultFileTypeCd" -> {
                value = resultFileTypeCd[detailCode]
            }
        }
    }
    return value
}

val brandStateCd: Map<String,String> = mapOf(
    "100" to "정상",
    "400" to "삭제",
) //브랜드 상태 코드

val campaignTypeCd: Map<String,String> = mapOf(
    "sb" to "SB",
    "sd" to "SD",
    "sp" to "SP",
) //캠페인 유형 코드

val companyStateCd: Map<String,String> = mapOf(
    "100" to "정상",
    "400" to "삭제",
) //회사 상태 코드

val countryStateCd: Map<String,String> = mapOf(
    "100" to "정상",
    "400" to "삭제",
) //국가 상태 코드

val memberStateCd: Map<String,String> = mapOf(
    "100" to "정상",
    "200" to "정지",
    "300" to "휴면",
    "400" to "탈퇴",
) //회원 상태 코드

val memberTypeCd: Map<String,String> = mapOf(
    "100" to "일반회원",
    "900" to "관리자",
) //회원 유형 코드

val resultFileTypeCd: Map<String,String> = mapOf(
    "100" to "판매데이터-매출",
    "101" to "판매데이터-상품",
    "102" to "광고데이터-캠페인",
    "103" to "광고데이터-상품",
    "104" to "광고데이터-키워드",
    "105" to "주문처리보고서",
    "106" to "재고현황",
) //성과 파일 유형 코드
