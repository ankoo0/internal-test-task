package com.andersen.nexxiot.db

interface SearchClientRepository {
    fun searchClients(query: String): List<ClientEntity>

}