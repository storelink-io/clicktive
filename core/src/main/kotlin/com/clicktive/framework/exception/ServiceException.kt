package com.clicktive.framework.exception

import com.clicktive.framework.api.getMessage
import java.lang.RuntimeException

open class ServiceException : RuntimeException {
    var messageCode: String
    final override lateinit var message: String

    constructor(messageCode: String) {
        this.messageCode = messageCode
        this.message = getMessage(messageCode)
    }

    constructor(messageCode: String = "ERR-001", message: String) : this(messageCode) {
        this.messageCode = messageCode
        this.message = message
    }

    constructor(messageCode: String = "ERR-001", messageParams: Array<String>) : this(messageCode) {
        this.messageCode = messageCode
        this.message = getMessage(messageCode)
        this.message = this.message.let { String.format(it, *messageParams) }
    }
}