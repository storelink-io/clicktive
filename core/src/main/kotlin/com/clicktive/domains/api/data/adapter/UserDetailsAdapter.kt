package com.clicktive.domains.api.data.adapter

import com.clicktive.domains.api.data.entity.member.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsAdapter(private val user: Member): UserDetails {
    fun getAuthUser(): Member = user

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities: ArrayList<GrantedAuthority> = ArrayList()
        authorities.add(SimpleGrantedAuthority("ADMIN"))
        return authorities
    }

    override fun getPassword(): String? = user.memberPw

    override fun getUsername(): String? = user.memberId

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}