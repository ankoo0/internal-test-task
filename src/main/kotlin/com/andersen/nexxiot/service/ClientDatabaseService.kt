package com.andersen.nexxiot.service

import com.andersen.nexxiot.db.ClientRepository
import com.andersen.nexxiot.domain.model.ClientCreateModel
import com.andersen.nexxiot.domain.model.ClientModel
import com.andersen.nexxiot.domain.request.ClientUpdateRequest
import com.andersen.nexxiot.domain.response.ClientResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class ClientDatabaseService(
    private val clientRepository: ClientRepository,
    private val clientMapper: ClientMapper,
) {

    @Transactional
     fun update(request: ClientUpdateRequest, id:UUID): ClientModel {
         val existingClient = clientRepository.findById(id).orElseThrow { NoSuchElementException("No such user") }
        clientMapper.updateEntity(existingClient,request)
        val updatedClient = clientRepository.save(existingClient)
        return clientMapper.toModel(updatedClient)
     }


        @Transactional(readOnly = true)
    fun searchClients(query: String, vararg fields:String): List<ClientModel> {
       return clientRepository.searchClients(query,*fields)
            .map { clientMapper.toModel(it) }
    }

    fun getById(id: UUID): ClientModel {
        return clientRepository
            .findById(id)
            .map { clientMapper.toModel(it) }
            .orElseThrow { NoSuchElementException("Client not found for id: $id") }
    }

    fun save(createModel: ClientCreateModel): ClientModel {
        val clientEntity = clientRepository.save(clientMapper.toEntity(createModel))
        return clientMapper.toModel(clientEntity)
    }

    fun getAllByPage(page: Int): Page<ClientModel> {
        val pageable = PageRequest.of(page - 1, 10)
        val clientPage = clientRepository.findAll(pageable)
        return clientPage.map { clientMapper.toModel(it) }
    }

    fun delete(id: UUID) = clientRepository.deleteById(id)

}