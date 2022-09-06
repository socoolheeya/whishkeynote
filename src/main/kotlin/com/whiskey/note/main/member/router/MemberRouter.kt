package com.whiskey.note.main.member.router

import com.whiskey.note.main.member.handler.MemberHandler
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.reactive.function.server.router


@Configuration
@RequiredArgsConstructor
class MemberRouter(
    private val memberHandler: MemberHandler
) {
    @Bean
    fun memberRouters() {
        router {
            accept(MediaType.APPLICATION_JSON).nest {
                GET("/members", memberHandler::getMembers)
                GET("/members/{id}", memberHandler::getMember)
                POST("/members", memberHandler::createMember)
                PUT("/members/{id}", memberHandler::updateMember)
                DELETE("/members/{id}", memberHandler::deleteMember)
            }
        }
    }
}
