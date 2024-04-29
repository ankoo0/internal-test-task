package com.andersen.nexxiot.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Operation(
    summary = "Searches clients by given query",
    description = "Provide search query to retrieve best matches found by client first and last name")
@ApiResponse(responseCode = "400", description = "Invalid query supplied")
@ApiResponse(responseCode = "404", description = "Query returned no results")
@ApiResponse(responseCode = "500", description = "Internal server error")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SearchClientByQuery()
