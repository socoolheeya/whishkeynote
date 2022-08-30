package com.whiskey.note.member.service

import com.whiskey.note.member.model.Member
import com.whiskey.note.member.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigInteger

@Service
class MemberService(
    val memberRepository: MemberRepository
) {

    suspend fun getMembers(): Flux<Member> {
        return memberRepository.findAll()
    }

    suspend fun getMember(memberId: Long): Mono<Member>? {
        return memberRepository.findById(memberId)
    }

    suspend fun saveMember(member: Member): Mono<Member>? {
        return memberRepository.save(member)
    }


    suspend fun deleteMember(memberId: Long): Mono<Void>? {
        return memberRepository.deleteById(memberId)
    }
}