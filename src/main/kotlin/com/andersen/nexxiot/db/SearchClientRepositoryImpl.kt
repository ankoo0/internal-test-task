package com.andersen.nexxiot.db

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.hibernate.search.mapper.orm.Search

class SearchClientRepositoryImpl(
    @PersistenceContext private var entityManager: EntityManager
) : SearchClientRepository {

    override fun searchClients(query: String,vararg fields: String): List<ClientEntity> {
        val searchSession = Search.session(entityManager)

        val result = searchSession.search(ClientEntity::class.java)
            .where { f ->
                f.match()
                    .fields(*fields)
                    .matching("$query*")
                    .fuzzy(1)
                    .analyzer("name")

            }
            .fetch(20)

        val hits = result.hits()

        return hits.map { it as ClientEntity }
    }

}