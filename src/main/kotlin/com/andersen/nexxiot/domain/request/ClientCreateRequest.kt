package com.andersen.nexxiot.domain.request

data class ClientCreateRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val job: String?,
    val occupation: String?
)
