package com.andersen.nexxiot.service

import com.andersen.nexxiot.domain.model.ClientModel
import com.andersen.nexxiot.domain.request.ClientCreateRequest
import com.andersen.nexxiot.domain.response.ClientResponse
import com.andersen.nexxiot.integration.GenderizeFeignClient
import com.andersen.nexxiot.integration.response.GenderizeResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ClientServiceImpl(
    private val clientMapper: ClientMapper,
    private val clientDatabaseService: ClientDatabaseService,
    private val genderizeFeignClient: GenderizeFeignClient
) : ClientService {

    override fun getById(id: UUID): ClientResponse {
        return clientMapper.toClientResponse(clientDatabaseService.getById(id))
    }

    override fun getAllByPage(page: Int): List<ClientResponse> {
        return clientDatabaseService.getAllByPage(page)
            .map { clientMapper.toClientResponse(it) }
    }

    override fun deleteById(id: UUID) {
        clientDatabaseService.delete(id)
    }

    @Transactional
    override fun create(request: ClientCreateRequest): ClientResponse {
        val clientCreateModel = clientMapper.toCreateModel(request)
        val resp = genderizeFeignClient.getGenderProbability(clientCreateModel.firstName)

        val clientModel: ClientModel
        if (resp.probability >= 0.8) {
            clientModel = clientDatabaseService.save(clientCreateModel)
        } else {
            throw Exception("Gender not detected")
        }

        return clientMapper.toClientResponse(clientModel)
    }

    override fun search(query: String): List<ClientResponse> {
        return clientDatabaseService.searchUsers(query).map { clientMapper.toClientResponse(it) }
    }

}