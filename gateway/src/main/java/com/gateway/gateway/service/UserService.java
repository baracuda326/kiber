package com.gateway.gateway.service;

import com.gateway.gateway.models.MyUserDetails;
import com.gateway.gateway.models.dto.RegistrationModel;
import com.gateway.gateway.models.dto.UserFullModel;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    //****************************************************************************

    UserFullModel registrationUser(String token, RegistrationModel registrationDto);

    //****************************************************************************
    UserFullModel login(MyUserDetails principal, HttpServletRequest request);
    //****************************************************************************

}
