package com.andersen.nexxiot.integration.response

data class GenderizeResponse(
    val count: Int,
    val name: String,
    val gender: String,
    val probability: Double
)