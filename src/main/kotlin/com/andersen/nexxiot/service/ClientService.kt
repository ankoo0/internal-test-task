package com.andersen.nexxiot.service

import com.andersen.nexxiot.domain.request.ClientCreateRequest
import com.andersen.nexxiot.domain.response.ClientResponse
import java.util.UUID

interface ClientService {

    fun getById(id: UUID): ClientResponse

    fun getAllByPage(page: Int): List<ClientResponse>

    fun deleteById(id: UUID)

    fun create(request: ClientCreateRequest): ClientResponse

}