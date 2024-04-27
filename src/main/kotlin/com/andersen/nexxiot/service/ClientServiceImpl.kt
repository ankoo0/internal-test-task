package com.andersen.nexxiot.service

import com.andersen.nexxiot.domain.request.ClientCreateRequest
import com.andersen.nexxiot.domain.response.ClientResponse
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ClientServiceImpl(
    private val clientMapper: ClientMapper,
    private val clientDatabaseService: ClientDatabaseService
) : ClientService {

    override fun getById(id: UUID): ClientResponse {
        return clientMapper.toClientResponse(clientDatabaseService.getById(id))
    }

    override fun getAllByPage(page: Int): List<ClientResponse> {
        return clientDatabaseService.getAllByPage(page).map { clientMapper.toClientResponse(it) }
    }

    override fun deleteById(id: UUID) {
        clientDatabaseService.delete(id)
    }

    override fun create(request: ClientCreateRequest): ClientResponse {
        val clientCreateModel = clientMapper.toCreateModel(request)
        val clientModel = clientDatabaseService.save(clientCreateModel)
        return clientMapper.toClientResponse(clientModel)
    }

}