package com.clicktive.framework.util

import java.util.*

object TypeCheckUtils {
    /**
     * 해당 값이 빈 값인지 체크
     *
     * @author      jenni
     * @since       2022-12-09
     * @param       Any?     빈 값 체크 할 값
     * @return      Boolean
     */
    fun Any?.isEmpty(): Boolean {
        val valueType = if(this == null) null else this::class.java
        return (valueType == emptyList<Any>()::class.java || valueType == emptyArray<Any>()::class.java)
    }

    /**
     * 해당 값이 basic type 값인지 체크
     *
     * @author      jenni
     * @since       2022-12-09
     * @param       Any?     빈 값 체크 할 값
     * @return      Boolean
     */
    fun Any?.isBasic(): Boolean {
        val valueType = if(this == null) null else this::class.java
        return (valueType == Short::class.java || valueType == Int::class.java || valueType == Integer::class.java || valueType == Long::class.java
                || valueType == Float::class.java || valueType == Double::class.java || valueType == Number::class.java
                || valueType == Char::class.java || valueType == String::class.java
                || valueType == Boolean::class.java || valueType == java.lang.Boolean::class.java)
    }

    /**
     * 해당 값이 void 값인지 체크
     *
     * @author      jenni
     * @since       2022-12-09
     * @param       Any?     void 값 체크 할 값
     * @return      Boolean
     */
    fun Any?.isVoid(): Boolean {
        val valueType = if(this == null) null else this::class.java
        return (valueType ==Void::class.java)
    }

    /**
     * 해당 값이 List인지 체크
     *
     * @author      jenni
     * @since       2022-11-30
     * @param       Any?     타입체크 할 값
     * @return      Boolean
     */
    fun Any?.isList(): Boolean {
        val valueType = if(this == null) null else this::class.java
        return (valueType == List::class.java || valueType == ArrayList::class.java || valueType == LinkedList::class.java
                || valueType == Collections.singletonList("").javaClass)
    }

    /**
     * 해당 값이 Map인지 체크
     *
     * @author      jenni
     * @since       2022-11-30
     * @param       Any?     타입체크 할 값
     * @return      Boolean
     */
    fun Any?.isMap(): Boolean {
        val valueType = if(this == null) null else this::class.java
        return (valueType == Map::class.java || valueType == HashMap::class.java || valueType == LinkedHashMap::class.java)
    }
}