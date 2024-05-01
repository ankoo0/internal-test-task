package com.andersen.nexxiot.integration

import com.andersen.nexxiot.domain.request.ClientCreateRequest
import com.andersen.nexxiot.domain.request.ClientUpdateRequest
import com.andersen.nexxiot.domain.response.ClientResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.jdbc.Sql
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Sql("/test-data.sql")
class ClientControllerTest @Autowired constructor(
    private var testRestTemplate: TestRestTemplate,
) {
    private val objectMapper = ObjectMapper().registerKotlinModule()

    companion object {
        @Container
        @ServiceConnection
        val container = PostgreSQLContainer<Nothing>("postgres:latest").apply {
            withDatabaseName("db")
            withUsername("user")
            withPassword("password")
        }

    }

    @Test
    fun `getAllClients endpoint should return a list of clients`() {
        println(container.isRunning)
        val responseEntity = testRestTemplate.getForEntity("/api/v1/clients", String::class.java)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertNotNull(responseEntity.body)
        println("Response from getAllClients endpoint: ${responseEntity.body}")
    }

    @Test
    fun `createClient endpoint should create a new client`() {
        val request = ClientCreateRequest(
            "John",
            "Doe",
            "fgfg@dot.com",
            "Grand Theatre",
            "Actor",
            "male"
        )
        val responseEntity = testRestTemplate.postForEntity("/api/v1/clients", request, String::class.java)
        assertEquals(HttpStatus.CREATED, responseEntity.statusCode)
    }


    @Test
    fun `createClient endpoint should return 409 when email already exists`() {
        val request = ClientCreateRequest(
            "John",
            "Doe",
            "example@dot.com",
            "Grand Theatre",
            "Actor",
            "male"
        )
        val responseEntity = testRestTemplate.postForEntity("/api/v1/clients", request, String::class.java)
        assertEquals(HttpStatus.CONFLICT, responseEntity.statusCode)
    }

    @Test
    fun `updateClientById endpoint should update an existing client`() {
        val clientId = UUID.fromString("51b5c313-a593-4ccd-a815-419a6e299242")
        val request = ClientUpdateRequest(
            "Jccohn",
            "Doe",
            "ecsdfle@dot.com",
            "Grand Theatre",
            "Actor",
            "male"
        )

        val responseEntity = testRestTemplate.exchange(
            "/api/v1/clients/$clientId",
            HttpMethod.PUT,
            HttpEntity(request),
            String::class.java
        )

        val updatedClient = objectMapper.readValue(responseEntity.body, ClientResponse::class.java)
        println(updatedClient)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(request.firstName, updatedClient.firstName)
        assertEquals(request.lastName, updatedClient.lastName)
        assertEquals(request.email, updatedClient.email)
        assertEquals(request.job, updatedClient.job)
        assertEquals(request.position, updatedClient.position)
        assertEquals(request.gender, updatedClient.gender)
    }

    @Test
    fun `deleteClientById endpoint should return a client`() {
        val clientId = UUID.randomUUID()
        val responseEntity = testRestTemplate.delete("/api/v1/clients/$clientId", String::class.java)
        assertEquals(HttpStatus.NOT_FOUND, responseEntity)
    }


}