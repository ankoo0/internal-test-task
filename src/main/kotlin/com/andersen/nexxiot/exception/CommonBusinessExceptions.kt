package com.andersen.nexxiot.exception

import org.springframework.http.HttpStatus

enum class CommonBusinessExceptions(val message: String, val httpStatus: HttpStatus)  {
    CLIENT_NOT_FOUND("Client with id %s not found", HttpStatus.NOT_FOUND),
    CLIENT_WITH_EMAIL_ALREADY_EXISTS("Client with email %s already exists", HttpStatus.CONFLICT),
    GENDER_NOT_DETECTED("Gender for first name %s is not detected", HttpStatus.BAD_REQUEST);

}