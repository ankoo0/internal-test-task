package com.andersen.nexxiot.db

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.core.annotation.MergedAnnotations.Search
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ClientRepository : JpaRepository<ClientEntity,UUID>{

    override fun findAll(pageable:Pageable): Page<ClientEntity>


}