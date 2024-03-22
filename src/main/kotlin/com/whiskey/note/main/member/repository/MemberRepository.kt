package com.whiskey.note.main.member.repository

import com.whiskey.note.main.member.model.Member
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.math.BigInteger

@Repository
interface MemberRepository: ReactiveCrudRepository<Member, BigInteger> {

    fun findByEmail(email: String): Mono<Member?>


}