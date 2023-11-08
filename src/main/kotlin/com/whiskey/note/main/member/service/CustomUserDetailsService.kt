package com.whiskey.note.main.member.service

import com.whiskey.note.main.member.model.CustomUserDetails
import com.whiskey.note.main.member.repository.MemberRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private var roles: List<String?>? = java.util.ArrayList()
): ReactiveUserDetailsService {

    override fun findByUsername(username: String): Mono<UserDetails> {

        var pwd: String = ""
        memberRepository.findByEmail(username)
            .subscribe {
                pwd = it.password.toString()
            }

        return CustomUserDetails(
            username,
            pwd,
            true,
            true,
            true,
            true
        ).toMono()

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