package com.gateway.gateway.service.impl;

import com.gateway.gateway.models.MyUserDetails;
import com.gateway.gateway.models.dto.MessageModel;
import com.gateway.gateway.models.dto.RegistrationModel;
import com.gateway.gateway.models.dto.UserFullModel;
import com.gateway.gateway.repository.KafkaSenderRepository;
import com.gateway.gateway.repository.UserRepository;
import com.gateway.gateway.service.AuthService;
import com.gateway.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.gateway.gateway.utils.UserMapper.mapperFromRegistrationDtoToAuthModel;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final KafkaSenderRepository kafkaSenderRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthService authService, KafkaSenderRepository kafkaSenderRepository) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.kafkaSenderRepository = kafkaSenderRepository;
    }

    @Override
    public UserFullModel registrationUser(String token, RegistrationModel registrationDto) {
        return userRepository.registrationUser(mapperFromRegistrationDtoToAuthModel(
                authService.registration(token), registrationDto));
    }

    @Override
    public UserFullModel login(MyUserDetails principal, HttpServletRequest request) {
        kafkaSenderRepository.postMessage(new MessageModel(principal.getEmail()));
        return null;
    }
}
