package com.andersen.nexxiot.db

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClientRepository : JpaRepository<ClientEntity,UUID>, SearchClientRepository{

    override fun findAll(pageable:Pageable): Page<ClientEntity>

}