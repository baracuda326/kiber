package com.gateway.gateway.service.impl;

import com.gateway.gateway.component.TokenComponent;
import com.gateway.gateway.exceptions.RegistrationModelException;
import com.gateway.gateway.models.AccountCredentials;
import com.gateway.gateway.repository.AuthRepository;
import com.gateway.gateway.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.gateway.gateway.utils.UserMapper.mapperFromRegistrationDtoToAuth;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final TokenComponent tokenComponent;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthServiceImpl(AuthRepository authRepository, TokenComponent tokenComponent
            , BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authRepository = authRepository;
        this.tokenComponent = tokenComponent;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String registration(String token) {
        AccountCredentials accountCredentials = tokenComponent.decodeToken(token);
        if (authRepository.existsById(accountCredentials.getEmail())) {
            throw new RegistrationModelException("User is present!");
        }
        authRepository.save(mapperFromRegistrationDtoToAuth(accountCredentials.getEmail()
                , bCryptPasswordEncoder.encode(accountCredentials.getPassword())));
        return accountCredentials.getEmail();
    }
}
