package com.whiskey.note.router

import com.whiskey.note.member.handler.MemberHandler
import kotlinx.coroutines.FlowPreview
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter


@Configuration
@RequiredArgsConstructor
class MemberRouter() {
    @Bean
    fun memberRouter(memberHandler: MemberHandler) = coRouter {
        GET("/members", memberHandler::getMembers)
        GET("/members/{id}", memberHandler::getMember)
        POST("/members", memberHandler::createMember)
        PUT("/members/{id}", memberHandler::updateMember)
        DELETE("/members/{id}", memberHandler::deleteMember)
    }
}
