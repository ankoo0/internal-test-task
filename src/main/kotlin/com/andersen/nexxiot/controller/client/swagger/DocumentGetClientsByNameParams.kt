package com.andersen.nexxiot.controller.client.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Operation(
    summary = "Searches clients by given first and last name",
    description = "Provide firstName and lastName or one of them to retrieve page of clients based on partial matches by one or two fields")
@ApiResponse(responseCode = "400", description = "Invalid parameters supplied")
@ApiResponse(responseCode = "404", description = "Query returned no results")
@ApiResponse(responseCode = "500", description = "Internal server error")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DocumentGetClientsByNameParams()
