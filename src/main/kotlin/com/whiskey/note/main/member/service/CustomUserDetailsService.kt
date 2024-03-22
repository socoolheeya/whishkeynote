package com.whiskey.note.main.member.service

import com.whiskey.note.main.member.model.CustomUserDetails
import com.whiskey.note.main.member.repository.MemberRepository
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.util.ObjectUtils
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Slf4j
@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository,
    private var roles: List<String?>? = java.util.ArrayList()
): ReactiveUserDetailsService {

    val log: Logger = LoggerFactory.getLogger(CustomUserDetailsService::class.java)

    override fun findByUsername(username: String): Mono<UserDetails> {
        var password = "";
        val member = memberRepository.findByEmail(username)
            .switchIfEmpty(Mono.error(UsernameNotFoundException("Not found member, username : $username")))

        member.subscribe {
            password = it?.password.toString()
        }

        return CustomUserDetails(
            username = username,
            password = password,
            isAccountNonLocked =  true,
            isAccountNonExpired = true,
            isCredentialsNonExpired = true,
            isEnabled = true
        ).toMono();
    }

    fun setRoles(roles: List<String?>) {
        this.roles = roles
    }

    fun getAuthorities(roles: List<String>): Collection<GrantedAuthority?>? {
        val authorities: MutableList<SimpleGrantedAuthority?> = ArrayList()
        for (role in roles) {
            authorities.add(SimpleGrantedAuthority("ROLE_$role"))
        }
        return authorities
    }
}