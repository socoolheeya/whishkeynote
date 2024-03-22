package com.whiskey.note.main.member.model

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import lombok.Builder
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigInteger
import java.time.LocalDateTime


@Builder
@Table(name = "member")
class Member(

    @field:Id
    @Column(value = "member_id")
    var memberId: BigInteger? = null,

    @Email
    @Column(value = "email")
    var email: String? = null,

    @field:NotBlank
    @Column(value = "password")
    var password: String? = null,

    @field:NotBlank
    @Column(value = "name")
    var name: String? = null,

    @field:Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    @Column(value = "phone")
    var phone: String? = null,

    @field:NotBlank
    var roles: List<Role>? = null,

    @field:CreatedDate
    @Column(value = "created_at")
    var createdAt: LocalDateTime? = null,

    @field:CreatedBy
    @Column(value = "created_by")
    var createdBy: String? = null,

    @field:LastModifiedDate
    @Column(value = "updated_at")
    var updatedAt: LocalDateTime? = null,

    @field:LastModifiedBy
    @Column(value = "updated_by")
    var updatedBy: String? = null
)