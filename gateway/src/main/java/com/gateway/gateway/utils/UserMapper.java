package com.gateway.gateway.utils;

import com.gateway.gateway.models.Role;
import com.gateway.gateway.models.dto.AuthModel;
import com.gateway.gateway.models.dto.RegistrationModel;
import com.gateway.gateway.models.entity.Auth;

import static com.gateway.gateway.utils.Constants.NOT_DETERMINED;

public final class UserMapper {
    private UserMapper() {
    }

    public static AuthModel mapperFromRegistrationDtoToAuthModel(String email, RegistrationModel registrationDto) {
        String firstName = registrationDto.getFirstName().trim().isEmpty() ? NOT_DETERMINED : registrationDto.getFirstName();
        String lastName = registrationDto.getLastName().trim().isEmpty() ? NOT_DETERMINED : registrationDto.getLastName();
        return AuthModel.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .role(Role.USER.toString())
                .build();
    }

    public static Auth mapperFromRegistrationDtoToAuth(String email, String encode) {
        return Auth.builder()
                .email(email)
                .password(encode)
                .roleAccess(Role.USER)
                .isActive(true)
                .build();
    }
}
