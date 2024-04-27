package com.andersen.nexxiot.controller

import com.andersen.nexxiot.constants.ClientUrlConstants.CREATE
import com.andersen.nexxiot.constants.ClientUrlConstants.DELETE
import com.andersen.nexxiot.constants.ClientUrlConstants.GET
import com.andersen.nexxiot.constants.ClientUrlConstants.GET_ALL
import com.andersen.nexxiot.domain.request.ClientCreateRequest
import com.andersen.nexxiot.domain.response.ClientResponse
import com.andersen.nexxiot.service.ClientService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class ClientController( private val clientService: ClientService) {

    @GetMapping(GET_ALL)
    fun getAllClients(@RequestParam("page") page: Int): List<ClientResponse> {
       return clientService.getAllByPage(page)
    }

    @DeleteMapping(DELETE)
    fun deleteClient(@PathVariable("id") id: UUID) {
        clientService.deleteById(id)
    }

    @PostMapping(CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun createClient(@RequestBody @Valid request: ClientCreateRequest): ClientResponse {
       return clientService.create(request)
    }

    @GetMapping(GET)
    fun getClientById(@PathVariable("id") id: UUID): ClientResponse {
        return clientService.getById(id)
    }

}