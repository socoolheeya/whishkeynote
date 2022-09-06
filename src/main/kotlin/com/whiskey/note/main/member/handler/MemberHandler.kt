package com.whiskey.note.main.member.handler

import com.whiskey.note.main.member.model.Member
import com.whiskey.note.main.member.service.MemberService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
@RequiredArgsConstructor
class MemberHandler(
    private val memberService: MemberService
) {

    fun getMembers(request: ServerRequest): Mono<ServerResponse> {
        val member = memberService.getMembers()
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(member, Member::class.java)
    }

    fun getMember(request: ServerRequest): Mono<ServerResponse> {
        val id = Integer.parseInt(request.pathVariable("memberId")).toBigInteger()
        val existMember = memberService.getMember(id)

        return existMember.let {
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(it, Member::class.java)
        }
    }

    fun createMember(request: ServerRequest): Mono<ServerResponse> {
        val member: Mono<Member> = request.bodyToMono(Member::class.java)

        return member.flatMap {
            ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(memberService.create(it), Member::class.java)
        }
    }

    fun updateMember(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("memberId").toBigInteger()
        val updateUser: Mono<Member> = request.bodyToMono(Member::class.java)

        return updateUser
            .flatMap { member ->
                ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(memberService.update(id, member), Member::class.java)
            }
    }

    fun deleteMember(request: ServerRequest): Mono<ServerResponse> {
        val id = Integer.parseInt(request.pathVariable("memberId")).toBigInteger();
        val deleteUser: Mono<Member> = request.bodyToMono(Member::class.java)

        return deleteUser
            .flatMap {
                ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(memberService.delete(id), Member::class.java)
            }

    }
}