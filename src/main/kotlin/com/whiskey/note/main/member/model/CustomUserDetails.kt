package com.whiskey.note.main.member.model

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

@NoArgsConstructor
@AllArgsConstructor
class CustomUserDetails(
    private var username: String,
    private var password: String,
    private var isAccountNonExpired: Boolean,
    private var isAccountNonLocked: Boolean,
    private var isCredentialsNonExpired: Boolean,
    private var isEnabled: Boolean,
    private var roles: List<String>? = ArrayList()
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles?.stream()?.map {
                role -> SimpleGrantedAuthority("ROLE_$role")
        }?.collect(Collectors.toSet()) ?: ArrayList()
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        return this.isAccountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return this.isAccountNonLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return this.isCredentialsNonExpired
    }

    override fun isEnabled(): Boolean {
        return this.isEnabled
    }
}