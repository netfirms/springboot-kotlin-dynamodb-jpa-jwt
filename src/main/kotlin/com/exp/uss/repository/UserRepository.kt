package com.exp.uss.repository

import com.exp.uss.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

/**
 * User: taweechaimaklay
 * Date: 28/12/2019 AD
 * Time: 11:06
 */
interface UserRepository : JpaRepository<User, kotlin.Int> {
    fun existsByUsername(username: String?): Boolean

    fun findByUsername(username: String?): User?

    @Transactional
    fun deleteByUsername(username: String?)
}