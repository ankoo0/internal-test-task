package com.andersen.nexxiot.domain.request

import com.andersen.nexxiot.constants.ValidationConstants.EMAIL_PATTERN
import com.andersen.nexxiot.constants.ValidationConstants.NAME_PATTERN
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern

data class ClientCreateRequest(

    @field:NotEmpty
    @field:Pattern(
        regexp = NAME_PATTERN,
        message = "Should contain only Latin letters, whitespace and hyphen"
    )
    val firstName: String,

    @field:NotEmpty
    @field:Pattern(
        regexp = NAME_PATTERN,
        message = "Should contain only Latin letters, whitespace and hyphen"
    )
    val lastName: String,

    @field:NotEmpty
    @field:Email(
        regexp = EMAIL_PATTERN,
        message = "Invalid email"
    )
    val email: String,

    val job: String?,

    val occupation: String?
)
