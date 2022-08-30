package com.whiskey.note.member.repository

import com.whiskey.note.member.model.Member
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface MemberRepository: R2dbcRepository<Member, BigInteger> {


}