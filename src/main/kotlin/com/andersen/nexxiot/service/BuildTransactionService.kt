package com.andersen.nexxiot.service

import com.andersen.nexxiot.db.ClientEntity
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.hibernate.search.mapper.orm.Search
import org.hibernate.search.mapper.orm.session.SearchSession
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class BuildTransactionService(
    @PersistenceContext val entityManager: EntityManager
) : ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        val searchSession: SearchSession = Search.session(entityManager)

        val indexer = searchSession.massIndexer(ClientEntity::class.java)
            .threadsToLoadObjects(7)

        indexer.startAndWait()
    }
}