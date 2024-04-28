package com.andersen.nexxiot.db

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

class SearchClientRepositoryImpl(
    @PersistenceContext private var entityManager: EntityManager
) : SearchClientRepository {

    override fun searchClients(query: String): List<ClientEntity> {
        val searchSession = org.hibernate.search.mapper.orm.Search.session(entityManager)

        val result = searchSession.search(ClientEntity::class.java)
            .where { f ->
                f.match()
                    .fields("firstName", "lastName")
                    .matching("$query*")
                    .fuzzy(1)
                    .analyzer("name")

            }
            .fetch(20)

        val hits = result.hits()

        return hits.map { it as ClientEntity }
    }
}