package com.exp.uss.service

import com.exp.uss.exception.CustomException
import com.exp.uss.model.User
import com.exp.uss.repository.UserRepository
import com.exp.uss.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest


/**
 * User: taweechaimaklay
 * Date: 28/12/2019 AD
 * Time: 10:59
 */
@Service
class UserService {

    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Autowired
    private val jwtTokenProvider: JwtTokenProvider? = null

    @Autowired
    private val authenticationManager: AuthenticationManager? = null

    fun signin(username: String?, password: String?): String? {
        return try {
            authenticationManager!!.authenticate(UsernamePasswordAuthenticationToken(username, password))
            jwtTokenProvider?.createToken(username, userRepository?.findByUsername(username)?.getRoles())
        } catch (e: AuthenticationException) {
            throw CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    fun signup(user: User): String? {
        return if (!userRepository?.existsByUsername(user.getUsername())!!) {
            user.setPassword(passwordEncoder!!.encode(user.getPassword()))
            userRepository?.save(user)
            jwtTokenProvider?.createToken(user.getUsername(), user.getRoles())
        } else {
            throw CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    fun delete(username: String?) {
        userRepository?.deleteByUsername(username)
    }

    fun search(username: String?): User? {
        return userRepository?.findByUsername(username)
                ?: throw CustomException("The user doesn't exist", HttpStatus.NOT_FOUND)
    }

    fun whoami(req: HttpServletRequest?): User? {
        return userRepository?.findByUsername(jwtTokenProvider?.getUsername(req?.let { jwtTokenProvider.resolveToken(it) }))
    }

    fun refresh(username: String?): String? {
        return jwtTokenProvider?.createToken(username, userRepository?.findByUsername(username)?.getRoles())
    }
}