package com.andersen.nexxiot.service.impl

import com.andersen.nexxiot.domain.model.ClientModel
import com.andersen.nexxiot.domain.request.ClientCreateRequest
import com.andersen.nexxiot.domain.response.ClientResponse
import com.andersen.nexxiot.exception.BusinessException
import com.andersen.nexxiot.exception.CommonBusinessExceptions
import com.andersen.nexxiot.exception.CommonBusinessExceptions.CLIENT_NOT_FOUND
import com.andersen.nexxiot.exception.CommonBusinessExceptions.CLIENT_WITH_EMAIL_ALREADY_EXISTS
import com.andersen.nexxiot.integration.GenderizeFeignClient
import com.andersen.nexxiot.integration.response.GenderizeResponse
import com.andersen.nexxiot.service.ClientMapper
import com.andersen.nexxiot.utli.KSelect.Companion.field
import org.instancio.Instancio
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class ClientServiceImplTest {

    @Mock
    private lateinit var clientMapper: ClientMapper

    @Mock
    private lateinit var clientDatabaseService: ClientDatabaseService

    @Mock
    private lateinit var genderizeFeignClient: GenderizeFeignClient

    @InjectMocks
    private lateinit var clientService: ClientServiceImpl

    @Test
    fun `create() when email already exists then throw BusinessException`() {
        val existingEmail = "john@example.com"
        val request = Instancio.of(ClientCreateRequest::class.java)
            .set(field(ClientCreateRequest::email), existingEmail)
            .create()
        val exceptionMessage = "Client with email $existingEmail already exists"


        `when`(clientDatabaseService.checkByEmail(existingEmail)).thenThrow(
            BusinessException(CLIENT_WITH_EMAIL_ALREADY_EXISTS, existingEmail)
        )

        val exception = assertThrows(BusinessException::class.java) {
            clientService.create(request)
        }


        verify(clientDatabaseService).checkByEmail(existingEmail)
        assertEquals(exceptionMessage, exception.message)
    }

    @Test
    fun `create() when gender is not detected then throw BusinessException`() {
        val request = Instancio.of(ClientCreateRequest::class.java)
            .set(field(ClientCreateRequest::gender), null)
            .create()
        val exceptionMessage = "Gender for first name ${request.firstName} is not detected"


        val response = Instancio.of(GenderizeResponse::class.java)
            .set(field(GenderizeResponse::gender), null)
            .set(field(GenderizeResponse::probability), 0.0)
            .create()
        `when`(genderizeFeignClient.getGenderProbability(request.firstName)).thenReturn(response)

        val exception = assertThrows<BusinessException> {
            clientService.create(request)
        }

        verify(clientDatabaseService).checkByEmail(request.email)
        assertEquals(exceptionMessage, exception.message)
    }

    @Test
    fun `getById() when client is found`() {
        val clientId = UUID.randomUUID()
        val clientModel = Instancio.of(ClientModel::class.java)
            .set(field(ClientModel::id), clientId)
            .create()
        val response = Instancio.of(ClientResponse::class.java)
            .set(field(ClientResponse::id), clientId)
            .create()

        `when`(clientDatabaseService.getById(clientId)).thenReturn(clientModel)
        `when`(clientMapper.toClientResponse(clientModel)).thenReturn(response)


        val result = clientService.getById(clientId)

        assertEquals(clientId, result.id)
    }

    @Test
    fun `getById() when client is not found then throw BusinessException`() {
        val clientId = UUID.randomUUID()
        val exceptionMessage = "Client with id $clientId not found"

        `when`(clientDatabaseService.getById(clientId)).thenThrow(BusinessException(CLIENT_NOT_FOUND, clientId))

        val exception = assertThrows<BusinessException> {
            clientService.getById(clientId)
        }

        assertEquals(exceptionMessage, exception.message)
    }

    @Test
    fun `deleteById() when deleting client id is not found then throw BusinessException`() {
        val clientId = UUID.randomUUID()
        val exceptionMessage = "Client with id $clientId not found"

        `when`(clientDatabaseService.deleteById(clientId)).thenThrow(BusinessException(CLIENT_NOT_FOUND, clientId))

        val exception = assertThrows<BusinessException> {
            clientService.deleteById(clientId)
        }

        assertEquals(exceptionMessage, exception.message)
    }

    @Test
    fun `searchClientsByName() when both first name and last name are blank then throw BusinessException`() {
        val firstName = ""
        val lastName = ""
        val exceptionMessage = CommonBusinessExceptions.EMPTY_NAME_PARAMETERS.message


        val exception =  assertThrows<BusinessException> {
            clientService.searchClientsByName(firstName, lastName)
        }
        assertEquals(exceptionMessage,exception.message)
    }


//    @Test
//    fun `create() when gender probability greater or equals to 0,8 then save client`() {
//        val request = Instancio.of(ClientCreateRequest::class.java)
//            .set(field(ClientCreateRequest::firstName), "John")
//            .create()
//        val clientCreateModel = Instancio.of(ClientCreateModel::class.java)
//            .set(field(ClientCreateModel::firstName), "John")
//            .create()
//        val response = Instancio.of(GenderizeResponse::class.java)
//            .set(field(GenderizeResponse::gender), "male")
//            .set(field(GenderizeResponse::probability), 0.8)
//            .create()
//        val clientModel =Instancio.of(ClientModel::class.java)
//            .set(field(ClientModel::firstName), "John")
//            .create()
//
//        `when`(genderizeFeignClient.getGenderProbability(clientCreateModel.firstName)).thenReturn(response)
//        // Mock the save method to return the clientModel
//        `when`(clientDatabaseService.save(clientCreateModel)).thenReturn(clientModel)
//
//        // Call the create method
//        val clientResponse = clientService.create(request)
//
//        // Verify that the client is saved with the correct clientCreateModel
//        verify(clientDatabaseService).save(clientCreateModel)
//
//        // Assert the response
//        assertEquals(request.firstName, clientResponse.firstName)
//        // Add more assertions based on the expected behavior of the create method
//    }
}