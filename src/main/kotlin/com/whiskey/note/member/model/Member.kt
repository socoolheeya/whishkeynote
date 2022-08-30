package com.whiskey.note.member.model

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.math.BigInteger
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Table(name = "member", indexes = [Index(columnList = "email")])
data class Member(
    @field:Id
    var id: BigInteger? = null,
    @field:Email
    var email: String? = null,
    @field:NotBlank
    var password: String? = null,
    @field:NotBlank
    var name: String? = null,
    @field:Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    var phone: String? = null,
    @field:NotBlank
    var role: String? = null,
    @field:CreatedDate
    var createdAt: LocalDateTime? = null,
    @field:CreatedBy
    var createdBy: String? = null,
    @field:LastModifiedDate
    var updatedAt: LocalDateTime? = null,
    @field:LastModifiedBy
    var updatedBy: String? = null
)