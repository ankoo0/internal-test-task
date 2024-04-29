package com.andersen.nexxiot.controller.client.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Operation(summary = "Creates new client", description = "Creates client with provided information based on JSON request")
@ApiResponse(responseCode = "400", description = "Invalid client information is passed")
@ApiResponse(responseCode = "201", description = "Client created successfully")
@ApiResponse(responseCode = "500", description = "Internal server error")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DocumentCreateClient()
