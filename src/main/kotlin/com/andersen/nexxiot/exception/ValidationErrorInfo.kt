package com.andersen.nexxiot.exception

data class ValidationErrorInfo(
    val message:String,
    val value: String,
    val field: String
)
