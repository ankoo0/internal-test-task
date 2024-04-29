package com.andersen.nexxiot.controller.client.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Operation(summary = "Deletes client", description = "Based on provided client id deletes him if client is present on storage")
@ApiResponse(responseCode = "400", description = "Invalid client id is passed")
@ApiResponse(responseCode = "200", description = "Client deleted successfully")
@ApiResponse(responseCode = "500", description = "Internal server error")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DocumentDeleteClientById()
