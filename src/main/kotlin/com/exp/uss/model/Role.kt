package com.exp.uss.model

import org.springframework.security.core.GrantedAuthority

/**
 * User: taweechaimaklay
 * Date: 28/12/2019 AD
 * Time: 11:07
 */
enum class Role : GrantedAuthority {
    ROLE_ADMIN, ROLE_CLIENT;

    override fun getAuthority(): String {
        return name
    }
}