package com.andersen.nexxiot.domain.request

import com.andersen.nexxiot.constants.ValidationConstants
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern

data class ClientUpdateRequest(

    @field:NotEmpty
    @field:Pattern(
        regexp = ValidationConstants.NAME_PATTERN,
        message = "Should contain only Latin letters, whitespace and hyphen"
    )
    val firstName: String,

    @field:NotEmpty
    @field:Pattern(
        regexp = ValidationConstants.NAME_PATTERN,
        message = "Should contain only Latin letters, whitespace and hyphen"
    )
    val lastName: String,

    @field:NotEmpty
    @field:Email(
        regexp = ValidationConstants.EMAIL_PATTERN,
        message = "Invalid email"
    )
    val email: String,

    val job: String?,

    val position: String?,

    val gender: String?
)
