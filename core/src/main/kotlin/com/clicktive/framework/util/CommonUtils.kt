package kr.placeup.framework.util

import org.hibernate.annotations.Comment
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.reflect.Field
import java.text.DecimalFormat

inline fun <reified T> getLoggerFor(): Logger = LoggerFactory.getLogger(T::class.java)

class CommonUtils {
    companion object {
        fun removeAll(source: String, removeValue: String) = source.replace(removeValue, "")

        fun removeBlank(source: String) = removeAll(source, " ")

        fun getComment(entityClass: Class<*>, columnName: String): String? = try {
            val column: Field = entityClass.getDeclaredField(columnName)
            val annotation: Comment = column.getAnnotation(Comment::class.java)
            annotation.value
        } catch (e: Exception) {
            null
        }

        fun nvl(source: String, defaultValue: String): String {
            return if (source.isBlank()) defaultValue else source
        }

        fun isNull(source: String?): Boolean {
            return source.isNullOrEmpty() || source.isBlank()
        }

        fun isNotNull(source: String?): Boolean {
            return !isNull(source)
        }

        fun generateNumber(number: Int = 999999) = (0..number).random()
            .toString()
            .padStart(number.toString().length, '0')

        fun convertCardNumber(cardNumber: String): String {
            var loopIndex = 0
            val newCardNumber: ArrayList<String> = arrayListOf()
            for (x in cardNumber){
                if (loopIndex in 4..11) {
                    newCardNumber.add("X")
                } else {
                    newCardNumber.add(x.toString())
                }
                loopIndex += 1
            }
            return newCardNumber.joinToString("")
        }

        fun getIntegerFormat(numberString: Int, pattern: String = "#,###"): String {
            val deciamlFormat = DecimalFormat(pattern)
            return deciamlFormat.format(numberString)
        }
    }
}