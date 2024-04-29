package com.andersen.nexxiot.service

import com.andersen.nexxiot.domain.request.ClientCreateRequest
import com.andersen.nexxiot.domain.request.ClientUpdateRequest
import com.andersen.nexxiot.domain.response.ClientResponse
import org.springframework.data.domain.Page
import java.util.UUID

interface ClientService {

    fun getById(id: UUID): ClientResponse

    fun getAllByPage(page: Int): Page<ClientResponse>

    fun deleteById(id: UUID)

    fun create(request: ClientCreateRequest): ClientResponse

    fun searchClientsByQuery(query:String): List<ClientResponse>

    fun updateById(id:UUID, request: ClientUpdateRequest): ClientResponse

    fun searchClientsByName(firstName:String, lastName:String): List<ClientResponse>

}