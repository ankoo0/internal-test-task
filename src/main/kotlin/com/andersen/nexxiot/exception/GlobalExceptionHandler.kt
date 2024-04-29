package com.andersen.nexxiot.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler
    fun handleGenericException(e: Exception) : ProblemDetail {
        logger.error(e.stackTraceToString())
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,e.message)
    }

    @ExceptionHandler
    fun handleBusinessException(e: BusinessException) : ProblemDetail {
        return ProblemDetail.forStatusAndDetail(e.getHttpStatus(),e.message)
    }

    @ExceptionHandler
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ProblemDetail{
        val bindingResult = e.bindingResult
        val fieldErrors = bindingResult.fieldErrors
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"Request contains constraint violations")
        problemDetail.setProperty("violations", mapToValidationErrors(fieldErrors) )
        return problemDetail
    }

    private fun mapToValidationErrors(fieldErrors: List<FieldError>): List<ValidationErrorInfo>{
        return fieldErrors.map {
            ValidationErrorInfo(
                it.defaultMessage.toString(),
                it.rejectedValue.toString(),
                it.field
            )
        }
    }

}