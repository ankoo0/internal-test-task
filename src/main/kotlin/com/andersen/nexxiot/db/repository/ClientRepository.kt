package com.andersen.nexxiot.db.repository

import com.andersen.nexxiot.db.ClientEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClientRepository : JpaRepository<ClientEntity,UUID>, SearchClientRepository {

    fun findByEmail(email:String) : Optional<ClientEntity>

    override fun findAll(pageable:Pageable): Page<ClientEntity>

}