package com.whiskey.note.main.member.model

import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigInteger

@Table(name = "role")
class Role(

    @field:Id
    @Column(value = "role_id")
    var roleId: BigInteger,

    @field:NotBlank
    @Column(value = "name")
    var name: String
)