package com.exp.uss.security

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


/**
 * User: taweechaimaklay
 * Date: 28/12/2019 AD
 * Time: 11:01
 */
class JwtTokenFilterConfigurer(jwtTokenProvider: JwtTokenProvider?) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    private var jwtTokenProvider: JwtTokenProvider? = null

    fun JwtTokenFilterConfigurer(jwtTokenProvider: JwtTokenProvider?) {
        this.jwtTokenProvider = jwtTokenProvider
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        val customFilter = JwtTokenFilter(jwtTokenProvider)
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}