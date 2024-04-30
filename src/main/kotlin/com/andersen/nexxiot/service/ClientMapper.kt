package com.andersen.nexxiot.service

import com.andersen.nexxiot.db.ClientEntity
import com.andersen.nexxiot.domain.model.ClientCreateModel
import com.andersen.nexxiot.domain.model.ClientModel
import com.andersen.nexxiot.domain.request.ClientCreateRequest
import com.andersen.nexxiot.domain.request.ClientUpdateRequest
import com.andersen.nexxiot.domain.response.ClientResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring")
interface ClientMapper {

    fun toEntity(createModel: ClientCreateModel): ClientEntity

    @Mapping(target = "id", ignore = true)
    fun updateEntity(@MappingTarget entity:ClientEntity , update: ClientUpdateRequest);

    fun toClientResponse(clientModel: ClientModel): ClientResponse

    fun toModel(entity: ClientEntity): ClientModel

    fun toModel(request: ClientCreateRequest): ClientModel

    fun toCreateModel(request:ClientCreateRequest): ClientCreateModel

}