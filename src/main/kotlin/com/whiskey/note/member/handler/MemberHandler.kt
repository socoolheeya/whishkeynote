package com.whiskey.note.member.handler

import com.whiskey.note.member.model.Member
import com.whiskey.note.member.repository.MemberRepository
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.json
import reactor.core.publisher.Mono

@Component
class MemberHandler(
    private var memberRepository: MemberRepository
) {

    suspend fun getMembers(request: ServerRequest): ServerResponse {
        val members = this.memberRepository.findAll().asFlow()
        return ServerResponse
            .ok()
            .json()
            .bodyAndAwait(members)
    }

    suspend fun getMember(request: ServerRequest): ServerResponse {
        val member = this.memberRepository.findById(request.pathVariable("memberId").toBigInteger())
            .asFlow()
        return ServerResponse
            .status(HttpStatus.OK)
            .json()
            .bodyAndAwait(member)
    }

    suspend fun createMember(request: ServerRequest): ServerResponse {
        val member: Mono<Member> = request.bodyToMono(Member::class.java)
            .switchIfEmpty(Mono.empty())
            .flatMap {
                member -> Mono.fromCallable {
                memberRepository.save(member)
                }.then(Mono.just(member))
            }

        return ServerResponse
            .status(HttpStatus.OK)
            .json()
            .buildAndAwait()
            ;
    }

    suspend fun updateMember(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("memberId").toBigInteger()
        val isExist = memberRepository.existsById(id).awaitSingle()

        return if (isExist) {
            val member = request.awaitBody<Member>().copy(id = id)
            memberRepository.save(member).awaitSingle()

            ServerResponse.ok().buildAndAwait()
        } else {
            ServerResponse.badRequest().buildAndAwait()
        }
    }

    suspend fun deleteMember(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("memberId").toBigInteger()
        val isExist = memberRepository.existsById(id).awaitSingle()

        return if (isExist) {
            memberRepository.deleteById(id).awaitFirstOrNull()
            ServerResponse.ok().buildAndAwait()
        } else {
            ServerResponse.badRequest().buildAndAwait()
        }
    }
}