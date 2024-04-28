package com.andersen.nexxiot.exception

import org.springframework.http.HttpStatus

enum class CommonBusinessExceptions(val message: String, val httpStatus: HttpStatus)  {
    CLIENT_NOT_FOUND("Client was not found", HttpStatus.NOT_FOUND),
    CLIENT_WITH_EMAIL_ALREADY_EXISTS("Client with this email already exists", HttpStatus.CONFLICT),
}