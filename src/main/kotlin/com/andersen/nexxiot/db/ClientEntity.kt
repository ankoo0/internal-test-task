package com.andersen.nexxiot.db

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "client")
data class ClientEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    var firstName: String,
    var lastName: String,
    var job: String?,
    var occupation: String?
)
