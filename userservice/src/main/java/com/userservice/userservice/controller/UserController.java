package com.userservice.userservice.controller;

import com.userservice.userservice.model.AuthModel;
import com.userservice.userservice.model.RegistrationModel;
import com.userservice.userservice.model.UserFullModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {
    @GetMapping(value = "/user")
    String get() {
        return "hello";
    }

    @PostMapping(value = "/user/registration")
    UserFullModel registrationUser(@RequestBody AuthModel authModel) {
        return UserFullModel.builder()
                .email(authModel.getEmail())
                .firstName(authModel.getFirstName())
                .lastName(authModel.getLastName())
                .aboutUser("about")
                .avatar("avatar")
                .phone("454545454")
                .role(authModel.getRole())
                .build();
    }

    //****************************************************************************
}
