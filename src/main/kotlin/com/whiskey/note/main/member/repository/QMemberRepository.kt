//package com.whiskey.note.main.member.repository
//
//import com.whiskey.note.main.member.model.Member
//import io.r2dbc.spi.Row
//import io.r2dbc.spi.RowMetadata
//import org.springframework.r2dbc.core.DatabaseClient
//import org.springframework.stereotype.Repository
//import org.springframework.web.reactive.function.client.WebClient
//import reactor.core.publisher.Mono
//import reactor.kotlin.core.publisher.toMono
//import java.math.BigInteger
//import java.util.function.BiFunction
//
//@Repository
//class QMemberRepository(private val databaseClient: DatabaseClient) {
//    fun getMemberByEmail(email: String): Mono<Member> {
//        return databaseClient
//            .sql("select * from member where email = :email")
//            .bind("email", email)
//            .map { t, u ->
//                Member(
//                    t.get("memberId", BigInteger::class.java),
//                    t.get("email", String::class.java),
//                    t.get("password", String::class.java),
//                    t.get("name", String::class.java)
//                )
//            }.one()
//        ;
//    }
//
//}