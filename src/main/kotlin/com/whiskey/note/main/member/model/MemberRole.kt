package com.whiskey.note.main.member.model

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigInteger

@Table(name = "member_role")
class MemberRole(

    @Column(value = "member_role_id")
    var memberRoleId: BigInteger,

    @Column(value = "member_id")
    var memberId: BigInteger,

    @Column(value = "role_id")
    var roleId: BigInteger
)