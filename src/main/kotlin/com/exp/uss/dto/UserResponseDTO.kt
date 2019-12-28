package com.exp.uss.dto

import com.exp.uss.model.Role
import io.swagger.annotations.ApiModelProperty

/**
 * User: taweechaimaklay
 * Date: 28/12/2019 AD
 * Time: 11:12
 */
class UserResponseDTO {

    @ApiModelProperty(position = 0)
    private var id: Int? = null
    @ApiModelProperty(position = 1)
    private var username: String? = null
    @ApiModelProperty(position = 2)
    private var email: String? = null
    @ApiModelProperty(position = 3)
    private var roles: List<Role?>? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

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

    fun getRoles(): List<Role?>? {
        return roles
    }

    fun setRoles(roles: List<Role?>?) {
        this.roles = roles
    }

}