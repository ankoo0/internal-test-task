package com.andersen.nexxiot.service

import com.andersen.nexxiot.db.ClientEntity
import com.andersen.nexxiot.db.ClientRepository
import com.andersen.nexxiot.domain.model.ClientCreateModel
import com.andersen.nexxiot.domain.model.ClientModel
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.hibernate.search.mapper.orm.Search
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*


@Service
class ClientDatabaseService(
    private val clientRepository: ClientRepository,
    private val clientMapper: ClientMapper,
    @PersistenceContext
private var entityManager: EntityManager
) {


      fun searchUsers(text: String): List<ClientModel> {
        val searchSession = Search.session(entityManager)

        val result = searchSession.search(ClientEntity::class.java)
            .where { f -> f.match()
                .fields("firstName", "lastName")
                .matching(text)
                .fuzzy(2)
                .analyzer("standard")
                .boost(5f)

            }

            .fetch(20)

        val hits = result.hits()
        println(hits.toString())
       return emptyList()
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