package com.andersen.nexxiot.exception

import org.springframework.http.HttpStatus

class BusinessException(private val commonException: CommonBusinessExceptions, cause: Any?=null) : RuntimeException() {


    private val msg: String = cause?.let {
        String.format(commonException.message, it)
    } ?: commonException.message

    private val httpStatus: HttpStatus = commonException.httpStatus

    override val message: String
        get() = this.msg

    fun getHttpStatus(): HttpStatus {
         return this.httpStatus
    }
}