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
        val authorities = ArrayList<SimpleGrantedAuthority>()
        roles?.let {
            authorities.addAll(
                it.stream()
                    .map { role -> SimpleGrantedAuthority(role) }
                    .collect(Collectors.toList())
            )
        }

        return authorities;
    }

    override fun getPassword(): String {
        return password;
    }

    override fun getUsername(): String {
        return username;
    }

    override fun isAccountNonExpired(): Boolean {
        return isAccountNonExpired;
    }

    override fun isAccountNonLocked(): Boolean {
        return isAccountNonLocked;
    }

    override fun isCredentialsNonExpired(): Boolean {
        return isCredentialsNonExpired;
    }

    override fun isEnabled(): Boolean {
        return isEnabled;
    }


}