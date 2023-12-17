package com.clicktive.framework.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class DateUtils {
    companion object {
        fun convertDt2String(sourceDt: LocalDateTime, format: String = "yyyy-MM-dd HH:mm:ss"): String {
            var formatter = DateTimeFormatter.ISO_DATE
            return sourceDt.format(DateTimeFormatter.ofPattern(format))
        }

        fun currentDt(format: String = "yyyyMMddHHmmss"): String {
            val now = LocalDateTime.now()
            var formatter = DateTimeFormatter.ISO_DATE
            return now.format(DateTimeFormatter.ofPattern(format))
        }

        fun currentDate(format: String = "-"): String {
            val now = LocalDate.now()
            var formatter = DateTimeFormatter.ISO_DATE

            if (format == "") {
                formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
            }
            return now.format(formatter)
        }

        fun minusDay(sourceDate: String, days: Int, isFromToday: Boolean = false): String {
            // 원하는 날짜(sourceDate)에 일수(days)를 차감 해서 날짜를 구함
            // 원하는 날짜를 포함 해서 계산을 하는 경우 ( isFromToday = true )
            val sourceLocalDate = LocalDate.parse(sourceDate, DateTimeFormatter.ISO_DATE)
            val realDays = if (isFromToday) days - 1 else days
            val targetDate = sourceLocalDate.minusDays(realDays.toLong())
            return targetDate.format(DateTimeFormatter.ISO_DATE)
        }

        fun addDay(sourceDate: String, days: Int, isFromToday: Boolean = true): String {
            // 원하는 날짜(sourceDate)에 일수(days)를 더해서 날짜를 구함
            // 원하는 날짜를 포함해서 계산을 하는 경우 ( isFromToday = true )
            val sourceLocalDate = LocalDate.parse(sourceDate, DateTimeFormatter.ISO_DATE)
            val realDays = if (isFromToday) days - 1 else days
            val targetDate = sourceLocalDate.plusDays(realDays.toLong())
            return targetDate.format(DateTimeFormatter.ISO_DATE)
        }

        fun addMinute(sourceDateTime: LocalDateTime, minute: Long): LocalDateTime {
            return sourceDateTime.plusMinutes(minute)
        }

        fun addMinute(minute: Long): LocalDateTime {
            return addMinute(LocalDateTime.now(), minute)
        }

        fun calculate(fromDate: String, endDate: String, isFromToday: Boolean = true): Int {
            //fromDate : 2023-10-01
            //endDate : 2023-10-05
            //isFromToday : true 이면 시작일자를 하루 포함 해서 계산함
            val fromLocalDate = LocalDate.parse(fromDate, DateTimeFormatter.ISO_DATE)
            val endLocalDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE)

            var days = ChronoUnit.DAYS.between(fromLocalDate, endLocalDate)
            if (isFromToday) days += 1
            return days.toInt()
        }
    }
}