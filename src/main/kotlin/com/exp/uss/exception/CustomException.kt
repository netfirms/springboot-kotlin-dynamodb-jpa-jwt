package com.exp.uss.exception

import org.springframework.http.HttpStatus;
/**
 * User: taweechaimaklay
 * Date: 28/12/2019 AD
 * Time: 11:09
 */
class CustomException(private val s: String, private val internalServerError: HttpStatus) : RuntimeException() {
    private val serialVersionUID = 1L

    private var message1: String? = null
    private var httpStatus: HttpStatus? = null

    fun getMessage1(): String? {
        return s
    }

    fun getHttpStatus(): HttpStatus? {
        return internalServerError
    }
}