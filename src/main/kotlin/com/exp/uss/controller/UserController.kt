package com.exp.uss.controller

import com.exp.uss.dto.UserDataDTO
import com.exp.uss.dto.UserResponseDTO
import com.exp.uss.model.User
import com.exp.uss.service.UserService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


/**
 * User: taweechaimaklay
 * Date: 28/12/2019 AD
 * Time: 11:13
 */
@RestController
@RequestMapping("/users")
class UserController {
    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val modelMapper: ModelMapper? = null

    @PostMapping("/signin")
    @ApiOperation(value = "\${UserController.signin}")
    @ApiResponses(value = [ApiResponse(code = 400, message = "Something went wrong"), ApiResponse(code = 422, message = "Invalid username/password supplied")])
    fun login( //
            @ApiParam("Username") @RequestParam username: String?,  //
            @ApiParam("Password") @RequestParam password: String?): String? {
        return userService!!.signin(username, password)
    }

    @PostMapping("/signup")
    @ApiOperation(value = "\${UserController.signup}")
    @ApiResponses(value = [ApiResponse(code = 400, message = "Something went wrong"), ApiResponse(code = 403, message = "Access denied"), ApiResponse(code = 422, message = "Username is already in use"), ApiResponse(code = 500, message = "Expired or invalid JWT token")])
    fun signup(@ApiParam("Signup User") @RequestBody user: UserDataDTO?): String? {
        return userService!!.signup(modelMapper!!.map(user, User::class.java))
    }

    @DeleteMapping(value = ["/{username}"])
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "\${UserController.delete}")
    @ApiResponses(value = [ApiResponse(code = 400, message = "Something went wrong"), ApiResponse(code = 403, message = "Access denied"), ApiResponse(code = 404, message = "The user doesn't exist"), ApiResponse(code = 500, message = "Expired or invalid JWT token")])
    fun delete(@ApiParam("Username") @PathVariable username: String?): String? {
        userService!!.delete(username)
        return username
    }

    @GetMapping(value = ["/{username}"])
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "\${UserController.search}", response = UserResponseDTO::class)
    @ApiResponses(value = [ApiResponse(code = 400, message = "Something went wrong"), ApiResponse(code = 403, message = "Access denied"), ApiResponse(code = 404, message = "The user doesn't exist"), ApiResponse(code = 500, message = "Expired or invalid JWT token")])
    fun search(@ApiParam("Username") @PathVariable username: String?): UserResponseDTO? {
        return modelMapper!!.map(userService!!.search(username), UserResponseDTO::class.java)
    }

    @GetMapping(value = ["/me"])
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "\${UserController.me}", response = UserResponseDTO::class)
    @ApiResponses(value = [ApiResponse(code = 400, message = "Something went wrong"), ApiResponse(code = 403, message = "Access denied"), ApiResponse(code = 500, message = "Expired or invalid JWT token")])
    fun whoami(req: HttpServletRequest?): UserResponseDTO? {
        return modelMapper!!.map(userService!!.whoami(req), UserResponseDTO::class.java)
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    fun refresh(req: HttpServletRequest): String? {
        return userService!!.refresh(req.remoteUser)
    }
}