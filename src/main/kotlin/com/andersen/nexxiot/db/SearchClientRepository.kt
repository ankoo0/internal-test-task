package com.andersen.nexxiot.db

interface SearchClientRepository {
    fun searchClients(query: String,vararg fields: String): List<ClientEntity>

}