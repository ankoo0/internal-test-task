package com.andersen.nexxiot.service.impl

import com.andersen.nexxiot.db.ClientRepository
import com.andersen.nexxiot.domain.model.ClientCreateModel
import com.andersen.nexxiot.domain.model.ClientModel
import com.andersen.nexxiot.domain.request.ClientUpdateRequest
import com.andersen.nexxiot.exception.BusinessException
import com.andersen.nexxiot.exception.CommonBusinessExceptions.CLIENT_NOT_FOUND
import com.andersen.nexxiot.exception.CommonBusinessExceptions.CLIENT_WITH_EMAIL_ALREADY_EXISTS
import com.andersen.nexxiot.service.ClientMapper
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

    fun checkByEmail(email: String) {
        clientRepository.findByEmail(email).ifPresent { client ->
            if (client.email == email)
                throw BusinessException(CLIENT_WITH_EMAIL_ALREADY_EXISTS, email)
        }
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
            .orElseThrow { BusinessException(CLIENT_NOT_FOUND,id) }
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

     fun deleteById(id: UUID) {
        val optionalClient = clientRepository.findById(id)


        optionalClient.ifPresentOrElse(
            { clientRepository.deleteById(id) },
            { throw BusinessException(CLIENT_NOT_FOUND, id) }
        )
    }
}