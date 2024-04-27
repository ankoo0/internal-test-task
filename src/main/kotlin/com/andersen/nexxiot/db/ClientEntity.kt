package com.andersen.nexxiot.db

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "client")
data class ClientEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @Column(name = "first_name")
    var firstName: String,

    @Column(name = "last_name")
    var lastName: String,

    @Column(name = "job")
    var job: String?,

    @Column(name = "occupation")
    var occupation: String?
)
