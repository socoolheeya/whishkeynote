package com.whiskey.note.main.member.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigInteger
import javax.validation.constraints.NotBlank

@Table(name = "role")
class Role(

    @field:Id
    @Column(value = "role_id")
    var roleId: BigInteger,

    @field:NotBlank
    @Column(value = "name")
    var name: String
)