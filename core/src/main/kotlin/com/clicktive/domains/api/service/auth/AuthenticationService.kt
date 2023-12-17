package com.clicktive.domains.api.service.auth

import com.clicktive.domains.api.repository.member.MemberRepository
import com.clicktive.domains.api.data.adapter.UserDetailsAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val userRepository: MemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetailsAdapter {
        return UserDetailsAdapter(
            userRepository.findById(username.toLong()).get()
        )
    }
}