package com.exp.uss.security

import com.exp.uss.model.User
import com.exp.uss.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * User: taweechaimaklay
 * Date: 28/12/2019 AD
 * Time: 11:03
 */
@Service
class MyUserDetails: UserDetailsService {
    @Autowired
    private val userRepository: UserRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val user: User = userRepository?.findByUsername(username)
                ?: throw UsernameNotFoundException("User '$username' not found")
        return org.springframework.security.core.userdetails.User
                .withUsername(username) //
                .password(user.getPassword()) //
                .authorities(user.getRoles()) //
                .accountExpired(false) //
                .accountLocked(false) //
                .credentialsExpired(false) //
                .disabled(false) //
                .build()
    }
}