package com.exp.uss.repository

import com.exp.uss.model.UserDynamo
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository
import java.util.*

/**
 * User: taweechaimaklay
 * Date: 28/12/2019 AD
 * Time: 13:41
 */
@EnableScan
interface UserDynamoRepository : CrudRepository<UserDynamo, String>{
}