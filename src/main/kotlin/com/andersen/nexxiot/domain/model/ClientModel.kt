package com.andersen.nexxiot.domain.model

import java.util.UUID

data class ClientModel(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val email: String,
    val job: String?,
    val position: String?,
    var gender: String?
)