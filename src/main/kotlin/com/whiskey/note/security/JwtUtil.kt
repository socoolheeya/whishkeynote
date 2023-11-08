package com.whiskey.note.security

import com.whiskey.note.main.member.model.Member
import com.whiskey.note.main.token.model.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date

@Component
class JwtUtil {
    private lateinit var key: Key
    private val secretKey: String = JwtProperties.SECRET_KEY
    private val expirationTime: Int = JwtProperties.EXPIRATION_TIME

    @PostConstruct
    fun init() {
        this.key = Keys.hmacShaKeyFor(secretKey.encodeToByteArray())
    }

    fun getAllClaimsFromToken(token: String): Claims =
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body

    fun getUsernameFromToken(token: String): String =
        getAllClaimsFromToken(token).subject

    fun getExpirationDateFromToken(token:String): Date =
        getAllClaimsFromToken(token).expiration

    fun isTokenExpired(token: String): Boolean  {
        val expiration: Date = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    fun createToken(member: Member): String  {
        val claims: MutableMap<String, String?> = mutableMapOf()
        claims["roles"] = member.roles?.get(0)?.name
        return doCreateToken(claims, member.email)
    }

    fun doCreateToken(claims: MutableMap<String, String?>, username: String?): String {
        val expirationTImeLong: Long = expirationTime.toLong()
        val createdDate: Date = Date()
        val expirationDate: Date = Date(createdDate.time + expirationTImeLong * 1000)

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(createdDate)
            .setExpiration(expirationDate)
            .signWith(key)
            .compact()
    }

    fun validateToken(token: String): Boolean =
        !isTokenExpired(token)

}