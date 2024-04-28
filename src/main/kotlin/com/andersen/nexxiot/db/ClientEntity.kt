package com.andersen.nexxiot.db

import jakarta.persistence.*
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField
import java.util.*
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed

@Entity
@Table(name = "client")
@Indexed
data class ClientEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @Column(name = "first_name")
    @FullTextField(analyzer = "name")
    var firstName: String,

    @Column(name = "last_name")
    @FullTextField(analyzer = "name")
    var lastName: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "job")
    var job: String?,

    @Column(name = "occupation")
    var position: String?
)
