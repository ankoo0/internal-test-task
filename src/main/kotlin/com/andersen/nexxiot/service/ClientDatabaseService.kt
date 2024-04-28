package com.andersen.nexxiot.service

import com.andersen.nexxiot.db.ClientRepository
import com.andersen.nexxiot.domain.model.ClientCreateModel
import com.andersen.nexxiot.domain.model.ClientModel
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
    fun searchUsers(query: String): List<ClientModel> {
       return clientRepository.searchClients(query)
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

    fun getAllByPage(page: Int): List<ClientModel> {
        val pageable = PageRequest.of(page - 1, 10)
        val clientPage = clientRepository.findAll(pageable)
        return clientPage.map { clientMapper.toModel(it) }.toList()
    }

    fun delete(id: UUID) = clientRepository.deleteById(id)

}