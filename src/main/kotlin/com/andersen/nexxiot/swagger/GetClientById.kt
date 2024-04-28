package com.andersen.nexxiot.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Operation(summary = "Get client by ID", description = "Client must exist")
@ApiResponse(responseCode = "400", description = "Invalid ID supplied")
@ApiResponse(responseCode = "404", description = "Customer not found")
@ApiResponse(responseCode = "500", description = "Internal server error")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class GetClientById()
