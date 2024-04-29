package com.andersen.nexxiot.controller.client.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Operation(
    summary = "Returns page of clients",
    description = "Based on provided page number returns corresponding page of clients if it exists")
@ApiResponse(responseCode = "400", description = "Invalid page passed to endpoint")
@ApiResponse(responseCode = "200", description = "Successfully retrieved page of clients")
@ApiResponse(responseCode = "500", description = "Internal server error")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DocumentGetAllClients()
