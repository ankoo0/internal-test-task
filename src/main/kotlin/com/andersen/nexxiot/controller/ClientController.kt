package com.andersen.nexxiot.controller

import com.andersen.nexxiot.constants.ClientUrlConstants.CREATE
import com.andersen.nexxiot.constants.ClientUrlConstants.DELETE
import com.andersen.nexxiot.constants.ClientUrlConstants.GET
import com.andersen.nexxiot.constants.ClientUrlConstants.GET_ALL
import com.andersen.nexxiot.constants.ClientUrlConstants.SEARCH_BY_NAME
import com.andersen.nexxiot.constants.ClientUrlConstants.SEARCH_BY_QUERY
import com.andersen.nexxiot.constants.ClientUrlConstants.UPDATE
import com.andersen.nexxiot.domain.request.ClientCreateRequest
import com.andersen.nexxiot.domain.request.ClientUpdateRequest
import com.andersen.nexxiot.domain.response.ClientResponse
import com.andersen.nexxiot.service.ClientService
import com.andersen.nexxiot.swagger.*
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class ClientController(private val clientService: ClientService) {

    @GetAllClients
    @GetMapping(GET_ALL)
    fun getAllClients(@RequestParam("page", defaultValue = "1") page: Int): Page<ClientResponse> {
        return clientService.getAllByPage(page)
    }

    @DeleteClientById
    @DeleteMapping(DELETE)
    fun deleteClientById(@PathVariable("id") id: UUID) {
        clientService.deleteById(id)
    }

    @CreateClient
    @PostMapping(CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun createClient(@RequestBody @Valid request: ClientCreateRequest): ClientResponse {
        return clientService.create(request)
    }

    @GetClientById
    @GetMapping(GET)
    fun getClientById(@PathVariable("id") id: UUID): ClientResponse {
        return clientService.getById(id)
    }

    @UpdateClientById
    @PutMapping(UPDATE)
    fun updateClientById(
        @PathVariable("id") id: UUID,
        @Valid @RequestBody request: ClientUpdateRequest
    ): ClientResponse {
        return clientService.updateById(id, request)
    }

    @SearchClientByQuery
    @GetMapping(SEARCH_BY_QUERY)
    fun getClientBySearchQuery(@RequestParam("query") query: String): List<ClientResponse> {
        return clientService.searchClientsByQuery(query)
    }

    @GetMapping(SEARCH_BY_NAME)
    fun getClientByNameParams(
        @RequestParam("firstName", defaultValue = "") firstName: String,
        @RequestParam("lastName", defaultValue = "") lastName: String
    ): List<ClientResponse> {
        return clientService.searchClientsByName(firstName, lastName)
    }

}