package com.andersen.nexxiot.domain.model

data class ClientCreateModel(
    val firstName: String,
    val lastName: String,
    val job: String?,
    val occupation: String?
)