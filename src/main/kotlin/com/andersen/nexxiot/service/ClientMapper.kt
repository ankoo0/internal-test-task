package com.andersen.nexxiot.service

import com.andersen.nexxiot.db.ClientEntity
import com.andersen.nexxiot.domain.model.ClientCreateModel
import com.andersen.nexxiot.domain.model.ClientModel
import com.andersen.nexxiot.domain.request.ClientCreateRequest
import com.andersen.nexxiot.domain.response.ClientResponse
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ClientMapper {

    fun toEntity(createModel: ClientCreateModel): ClientEntity

    fun toClientResponse(clientModel: ClientModel): ClientResponse

    fun toModel(entity: ClientEntity): ClientModel

    fun toCreateModel(request:ClientCreateRequest): ClientCreateModel

}