package com.whiskey.note.main.member.repository

import com.whiskey.note.main.member.model.Role
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface RoleRepository: ReactiveCrudRepository<Role, BigInteger> {
}