package com.andersen.nexxiot.service

import com.andersen.nexxiot.model.request.ClientCreateRequest
import com.andersen.nexxiot.model.response.ClientResponse

interface ClientService {

    fun getById(id: Int): ClientResponse

    fun getAllByPage(page: Int): List<ClientResponse>

    fun deleteById(id: Int)

    fun save(request: ClientCreateRequest): ClientResponse

}