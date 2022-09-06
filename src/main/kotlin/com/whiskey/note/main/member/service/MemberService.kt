package com.whiskey.note.main.member.service

import com.whiskey.note.main.member.model.Member
import com.whiskey.note.main.member.repository.MemberRepository
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigInteger

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun getMembers(): Flux<Member> =
        memberRepository.findAll()

    fun getMember(memberId: BigInteger): Mono<Member> =
        memberRepository.findById(memberId)

    fun getMemberByEmail(email: String): Mono<Member> =
        memberRepository.findByEmail(email)

    @Transactional
    fun create(member: Member): Mono<Member> =
        memberRepository.save(member)

    @Transactional
    fun update(memberId: BigInteger, member: Member): Mono<Member> {
        return memberRepository.findById(memberId)
            .flatMap {
                it.email = member.email
                it.name = member.name
                it.phone = member.phone
                memberRepository.save(it)
            }
    }

    @Transactional
    fun delete(memberId: BigInteger): Mono<Member> {
        return memberRepository.findById(memberId)
            .flatMap {
                memberRepository.delete(it)
                    .then(Mono.just(it))
            }
    }
}