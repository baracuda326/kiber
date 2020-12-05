package com.gateway.gateway.controller;

import com.gateway.gateway.models.MyUserDetails;
import com.gateway.gateway.models.dto.RegistrationModel;
import com.gateway.gateway.models.dto.UserFullModel;
import com.gateway.gateway.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
@CrossOrigin
public class UserController {
    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Registration new user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserFullModel.class),
            @ApiResponse(code = 400, message = "Error! Bad email or password"),
            @ApiResponse(code = 403, message = "Error! Forbidden"),
            @ApiResponse(code = 409, message = "Error! User already exist")
    })
    @PostMapping(value = "/user/registration")
    UserFullModel registrationUser(@RequestHeader("Authorization") String token
            , @RequestBody RegistrationModel registrationDto, HttpServletRequest request) {
        LOGGER.info("I'm here in service calling service two");
        return userService.registrationUser(token, registrationDto);
    }

    //****************************************************************************

    @ApiOperation(value = "Authorization user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserFullModel.class),
            @ApiResponse(code = 400, message = "Error! Bad token"),
            @ApiResponse(code = 401, message = "Error! Unauthorized"),
            @ApiResponse(code = 404, message = "Error! Not found")
    })

    @GetMapping(value = "/user/login")
    UserFullModel authUser(HttpServletRequest request){
        MyUserDetails principal = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.login(principal,request);
    }

    //****************************************************************************

    @ApiOperation(value = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK!"),
            @ApiResponse(code = 400, message = "Error! Bad request"),
            @ApiResponse(code = 401, message = "Error! User unauthorized"),
            @ApiResponse(code = 404, message = "Error! User not found"),
            @ApiResponse(code = 403, message = "Error! Forbidden")
    })

    @DeleteMapping(value = "/user/remove")
    void deleteUser(HttpServletRequest request){
        MyUserDetails principal = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    //****************************************************************************
}
