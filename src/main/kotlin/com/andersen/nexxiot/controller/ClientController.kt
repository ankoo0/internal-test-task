package com.andersen.nexxiot.controller

import com.andersen.nexxiot.constants.ClientUrlConstants.BASE
import com.andersen.nexxiot.constants.ClientUrlConstants.CREATE
import com.andersen.nexxiot.constants.ClientUrlConstants.DELETE
import com.andersen.nexxiot.constants.ClientUrlConstants.GET
import com.andersen.nexxiot.constants.ClientUrlConstants.GET_ALL
import com.andersen.nexxiot.model.request.ClientCreateRequest
import com.andersen.nexxiot.model.response.ClientResponse
import com.andersen.nexxiot.service.ClientService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(BASE)
class ClientController(val clientService: ClientService) {

    @GetMapping(GET_ALL)
    fun getAllClients(@RequestParam("page") page: Int?): List<ClientResponse> {
        TODO("Provide the return value")
    }

    @DeleteMapping(DELETE)
    fun deleteClient(@PathVariable("id") page: Int?) {

    }

    @PostMapping(CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    fun createClient(@RequestBody @Valid request: ClientCreateRequest): ClientResponse {
        TODO("Provide the return value")
    }

    @GetMapping(GET)
    fun getClientById(@PathVariable("id") id: Int): ClientResponse {
         TODO("Provide the return value")
    }

}