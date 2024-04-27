package com.andersen.nexxiot.service

import com.andersen.nexxiot.db.ClientRepository
import com.andersen.nexxiot.model.request.ClientCreateRequest
import com.andersen.nexxiot.model.response.ClientResponse
import org.springframework.stereotype.Service

@Service
class ClientServiceImpl(val clientRepository:ClientRepository) : ClientService {

    override fun getById(id: Int): ClientResponse {
        TODO("Not yet implemented")
    }

    override fun getAllByPage(page: Int): List<ClientResponse> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun save(request: ClientCreateRequest): ClientResponse {
        TODO("Not yet implemented")
    }


}