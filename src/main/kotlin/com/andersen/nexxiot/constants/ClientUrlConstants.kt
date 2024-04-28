package com.andersen.nexxiot.constants

object ClientUrlConstants {
    private const val RESOURCE_NAME = "/clients"
    private const val ID = "/{id}"
    const val BASE = BaseUrlConstants.BASE_V1 + RESOURCE_NAME
    const val GET = BASE + ID
    const val UPDATE = BASE + ID
    const val CREATE = BASE
    const val GET_ALL = BASE
    const val DELETE = BASE + ID
    const val SEARCH = "$BASE/search"

}