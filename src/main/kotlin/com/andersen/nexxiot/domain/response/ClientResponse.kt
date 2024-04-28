package com.andersen.nexxiot.domain.response

import java.util.UUID

data class ClientResponse(
    val id:UUID,
    val firstName: String,
    val lastName: String,
    val email: String,
    val job: String?,
    val position: String?,
    val gender: String?
)
