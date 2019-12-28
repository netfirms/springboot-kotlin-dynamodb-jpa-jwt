package com.exp.uss

import com.exp.uss.model.Role
import com.exp.uss.model.User
import com.exp.uss.model.UserDynamo
import com.exp.uss.repository.UserDynamoRepository
import com.exp.uss.service.UserService
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.*


@SpringBootApplication
@EnableJpaRepositories(excludeFilters = [
    ComponentScan.Filter(type = FilterType.ANNOTATION, classes = [EnableScan::class])
])
class UssApplication {

    private val logger: Logger = getLogger(UssApplication::class.java)

    @Bean
    fun modelMapper(): ModelMapper? {
        return ModelMapper()
    }

    @Bean
    fun init(userService: UserService, userDynamoRepository: UserDynamoRepository) = CommandLineRunner {
        // Test create user and add to MySQL
        val admin = User()
        admin.setUsername("admin")
        admin.setPassword("admin")
        admin.setEmail("admin@email.com")
        admin.setRoles(ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)))

        userService?.signup(admin);

        val client = User()
        client.setUsername("client")
        client.setPassword("client")
        client.setEmail("client@email.com");
        client.setRoles(ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)))

        // Test create user and add to dynamodb table
        userService?.signup(client)
        var userDynamo = UserDynamo()
        val roles: MutableSet<Role> = HashSet()
        roles.add(Role.ROLE_ADMIN)
        roles.add(Role.ROLE_CLIENT)
        userDynamo.setUsername("test")
        userDynamo.setPassword("1234")
        userDynamo.setRoles(roles)
        userDynamo.phoneNumber = "12345678901"
        userDynamoRepository.save(userDynamo)
    }
}

fun main(args: Array<String>) {
    runApplication<UssApplication>(*args)
}
