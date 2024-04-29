package com.andersen.nexxiot.constants

object ClientUrlConstants {
    private const val RESOURCE_NAME = "/clients"
    private const val ID = "/{id}"
    private const val CLIENT_BASE = BaseUrlConstants.BASE_V1 + RESOURCE_NAME
    const val GET = CLIENT_BASE + ID
    const val UPDATE = CLIENT_BASE + ID
    const val CREATE = CLIENT_BASE
    const val GET_ALL = CLIENT_BASE
    const val DELETE = CLIENT_BASE + ID
    const val SEARCH_BY_QUERY = "$CLIENT_BASE/search"
    const val SEARCH_BY_NAME = "$CLIENT_BASE/searchByName"

}