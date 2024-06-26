package com.andersen.nexxiot.domain.model

data class ClientCreateModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val job: String?,
    val position: String?,
    var gender: String?
)