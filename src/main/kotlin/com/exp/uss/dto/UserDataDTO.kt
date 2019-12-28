package com.exp.uss.dto

import com.exp.uss.model.Role
import io.swagger.annotations.ApiModelProperty

/**
 * User: taweechaimaklay
 * Date: 28/12/2019 AD
 * Time: 11:11
 */
class UserDataDTO {

    @ApiModelProperty(position = 0)
    private var username: String? = null
    @ApiModelProperty(position = 1)
    private var email: String? = null
    @ApiModelProperty(position = 2)
    private var password: String? = null
    @ApiModelProperty(position = 3)
    private var roles: List<Role?>? = null

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun getRoles(): List<Role?>? {
        return roles
    }

    fun setRoles(roles: List<Role?>?) {
        this.roles = roles
    }

}