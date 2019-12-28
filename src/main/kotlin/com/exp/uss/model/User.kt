package com.exp.uss.model

import com.exp.uss.UssApplication
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Size


/**
 * User: taweechaimaklay
 * Date: 28/12/2019 AD
 * Time: 11:08
 */
@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Int? = null

    @Column(unique = true, nullable = false)
    private var username: @Size(min = 4, max = 255, message = "Minimum username length: 4 characters") String? = null

    @Column(unique = true, nullable = false)
    private var email: String? = null

    private var password: @Size(min = 8, message = "Minimum password length: 8 characters") String? = null

    private var roles: String? = null

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

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun setRoles(roles: List<Role>) {
        print("User Created Enum ${roles.toString()}")
        this.roles = roles.toString();
    }

    fun getRoles(): List<Role>? {
        val list: MutableList<Role> = ArrayList()

//        for (s in this.roles!!) {
//            list.add(Role.valueOf(s))
//        }
        return list
    }
}