package com.andersen.nexxiot.db.repository

import com.andersen.nexxiot.db.ClientEntity

interface SearchClientRepository {
    fun searchClients(query: String,vararg fields: String): List<ClientEntity>

}