//package com.whiskey.note.security
//
//import com.whiskey.note.main.member.model.Member
//import com.whiskey.note.main.member.service.CustomUserDetailsService
//import com.whiskey.note.main.member.service.MemberService
//import com.whiskey.note.main.token.model.JwtProperties
//import com.whiskey.note.main.token.model.JwtToken
//import io.jsonwebtoken.Claims
//import io.jsonwebtoken.ExpiredJwtException
//import io.jsonwebtoken.Jws
//import io.jsonwebtoken.Jwts
//import io.jsonwebtoken.MalformedJwtException
//import io.jsonwebtoken.SignatureAlgorithm
//import io.jsonwebtoken.UnsupportedJwtException
//import io.jsonwebtoken.io.Decoders
//import io.jsonwebtoken.security.Keys
//import lombok.extern.slf4j.Slf4j
//import org.slf4j.LoggerFactory
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.stereotype.Component
//import org.springframework.util.StringUtils
//import org.springframework.web.server.ServerWebExchange
//import java.math.BigInteger
//import java.security.Key
//import java.util.*
//import java.util.stream.Collectors
//import javax.servlet.http.HttpServletRequest
//
//@Slf4j
//@Component
//class JwtTokenProvider(
//    private val jwtProperties: JwtProperties,
//    private val memberService: MemberService,
//    private val userDetailsService: CustomUserDetailsService
//) {
//    private val log = LoggerFactory.getLogger(JwtTokenProvider::class.java)
//
//    private fun getSignInKey(): Key {
//        val keyBytes = Decoders.BASE64.decode(JwtProperties.SECRET_KEY)
//        return Keys.hmacShaKeyFor(keyBytes)
//    }
//
//    fun createToken(authentication: Authentication): JwtToken  {
//        var member: Member? = Member()
//
//         memberService.getMemberByEmail(authentication.name)
//            .subscribe { m ->
//                member = m
//            }
//
//        val roles: String = authentication.authorities
//            .stream()
//            .map(GrantedAuthority::getAuthority)
//            .collect(Collectors.joining(","));
//
//        val claims: Claims = Jwts.claims()
//        claims["memberId"] = member?.memberId
//        claims["email"] = authentication.name
//        claims["roles"] = roles
//
//        val token: String = Jwts.builder()
//            .setSubject(authentication.name)
//            .addClaims(claims)
//            .setExpiration(Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
//            .signWith(this.getSignInKey(), SignatureAlgorithm.ES512)
//            .compact()
//
//        return JwtToken(BigInteger(authentication.name), token)
//    }
//
//
//    fun resolveToken(request: HttpServletRequest): String? {
//        val bearerToken = request.getHeader(JwtProperties.HEADER_STRING)
//        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtProperties.TOKEN_PREFIX)) {
//            bearerToken.substring(7)
//        } else null
//    }
//
//    fun decodeJwtToken(jwtToken: String): Jws<Claims> {
//        return Jwts.parserBuilder()
//            .setSigningKey(getSignInKey())
//            .build()
//            .parseClaimsJws(jwtToken)
//    }
//
//    fun getSubject(token: String): String {
//        return Jwts.parserBuilder()
//            .setSigningKey(getSignInKey())
//            .build()
//            .parseClaimsJws(token)
//            .body
//            .subject
//    }
//
//    fun getClaims(token: String): Claims {
//        return Jwts.parserBuilder()
//            .setSigningKey(getSignInKey())
//            .build()
//            .parseClaimsJws(token)
//            .body
//    }
//
//    fun getAuthentication(token: String): Authentication {
//        val str = getClaims(token)!!["roles"] as String?
//        val tokens: MutableList<String> = ArrayList()
//        val tokenizer = StringTokenizer(str, ",")
//        while (tokenizer.hasMoreElements()) {
//            tokens.add(tokenizer.nextToken())
//        }
//        userDetailsService.setRoles(tokens)
//
//        val email: String = (this.decodeJwtToken(token)?.getBody()?.get("loginAccount") as String)
//        val userDetails = userDetailsService.findByUsername(email)
//        var authorities: Collection<out GrantedAuthority> = ArrayList()
//        userDetails.subscribe {
//            authorities = it.authorities
//        }
//        return UsernamePasswordAuthenticationToken(
//            userDetails,
//            null,
//            authorities
//        )
//    }
//
//    fun extractToken(request: ServerWebExchange): String? =
//        request.response.headers.get("Authorization")?.get(0)
//
//    fun extractToken(request: HttpServletRequest): String? =
//        request.getHeader("Authorization")
//
//    fun validateToken(jwtToken: String?): Boolean {
//        return try {
//            val claims = Jwts.parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(jwtToken)
//            !claims.body.expiration.before(Date())
//        } catch (me: MalformedJwtException) {
//            log.error("Invalid JWT token")
//            false
//        } catch (eje: ExpiredJwtException) {
//            log.error("Expired JWT token")
//            false
//        } catch (uje: UnsupportedJwtException) {
//            log.error("Unsupported JWT token")
//            false
//        } catch (iae: IllegalArgumentException) {
//            log.error("JWT claims string is empty")
//            false
//        } catch (e: Exception) {
//            log.error("JWT token provider error")
//            false
//        }
//    }
//}