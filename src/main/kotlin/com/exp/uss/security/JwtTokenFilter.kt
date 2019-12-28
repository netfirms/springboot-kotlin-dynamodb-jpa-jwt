package com.exp.uss.security

import com.exp.uss.exception.CustomException
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * User: taweechaimaklay
 * Date: 28/12/2019 AD
 * Time: 11:00
 */
class JwtTokenFilter(jwtTokenProvider: JwtTokenProvider?) : OncePerRequestFilter() {

    private var jwtTokenProvider: JwtTokenProvider? = null

    fun JwtTokenFilter(jwtTokenProvider: JwtTokenProvider?) {
        this.jwtTokenProvider = jwtTokenProvider
    }

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, filterChain: FilterChain) {
        val token: String? = jwtTokenProvider?.resolveToken(httpServletRequest)
        try {
            if (token != null && jwtTokenProvider!!.validateToken(token)) {
                val auth: Authentication? = jwtTokenProvider?.getAuthentication(token)
                SecurityContextHolder.getContext().authentication = auth
            }
        } catch (ex: CustomException) { //this is very important, since it guarantees the user is not authenticated at all
            SecurityContextHolder.clearContext()
            ex.getHttpStatus()?.value()?.let { httpServletResponse.sendError(it, ex.message) }
            return
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse)
    }
}