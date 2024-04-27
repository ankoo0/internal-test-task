package com.andersen.nexxiot.domain.model

import java.util.UUID

data class ClientModel(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val job: String?,
    val occupation: String?
)