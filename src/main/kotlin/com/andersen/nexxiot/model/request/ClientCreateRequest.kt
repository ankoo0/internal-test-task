package com.andersen.nexxiot.model.request

data class ClientCreateRequest(
    val firstName: String,
    val lastName: String,
    val job: String?,
    val occupation: String?
)
