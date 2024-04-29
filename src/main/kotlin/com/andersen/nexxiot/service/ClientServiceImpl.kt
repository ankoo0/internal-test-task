package com.andersen.nexxiot.service

import com.andersen.nexxiot.domain.model.ClientModel
import com.andersen.nexxiot.domain.request.ClientCreateRequest
import com.andersen.nexxiot.domain.request.ClientUpdateRequest
import com.andersen.nexxiot.domain.response.ClientResponse
import com.andersen.nexxiot.exception.BusinessException
import com.andersen.nexxiot.exception.CommonBusinessExceptions
import com.andersen.nexxiot.integration.GenderizeFeignClient
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ClientServiceImpl(
    private val clientMapper: ClientMapper,
    private val clientDatabaseService: ClientDatabaseService,
    private val genderizeFeignClient: GenderizeFeignClient
) : ClientService {

    override fun getById(id: UUID): ClientResponse {
        return clientMapper.toClientResponse(clientDatabaseService.getById(id))
    }

    override fun getAllByPage(page: Int): Page<ClientResponse> {
        return clientDatabaseService.getAllByPage(page)
            .map { clientMapper.toClientResponse(it) }
    }

    override fun deleteById(id: UUID) {
        clientDatabaseService.deleteById(id)
    }

    @Transactional
    override fun create(request: ClientCreateRequest): ClientResponse {

        clientDatabaseService.checkByEmail(request.email)

        val clientCreateModel = clientMapper.toCreateModel(request)
        val resp = genderizeFeignClient.getGenderProbability(clientCreateModel.firstName)

        val clientModel: ClientModel
        if (resp.probability >= 0.8) {
            clientCreateModel.gender= resp.gender.uppercase(Locale.getDefault())
            clientModel = clientDatabaseService.save(clientCreateModel)
        } else {
            throw BusinessException(CommonBusinessExceptions.GENDER_NOT_DETECTED,request.firstName)
        }

        return clientMapper.toClientResponse(clientModel)
    }

    override fun searchClientsByQuery(query: String): List<ClientResponse> {
        return searchClients(query, listOf("firstName","lastName"))
    }

    private fun searchClients(query: String, fields: List<String>): List<ClientResponse> {
        return clientDatabaseService.searchClients(query, *fields.toTypedArray())
            .map { clientMapper.toClientResponse(it) }
    }

    // probably, specifications is more suitable here but as you limit task to just first and last name I will leave as-is
    override fun searchClientsByName(firstName: String, lastName: String): List<ClientResponse> {
        val query = when {
            firstName.isBlank() && lastName.isBlank() -> throw IllegalArgumentException("At least one of firstName or lastName parameters must be provided")
            firstName.isBlank() && lastName.isNotBlank() -> lastName
            firstName.isNotBlank() && lastName.isBlank() -> firstName
            else -> "$firstName $lastName"
        }

        val fields = mutableListOf<String>()

            fields.apply {
                when {
                    firstName.isNotBlank() -> add("firstName")
                    lastName.isNotBlank() -> add("lastName")
                }
            }

        return searchClients(query, fields)
    }

    @Transactional
    override fun updateById(id:UUID, request: ClientUpdateRequest): ClientResponse {
        clientDatabaseService.checkByEmail(request.email)
        val clientModel = clientDatabaseService.update(request, id)
        return clientMapper.toClientResponse(clientModel)
    }


}