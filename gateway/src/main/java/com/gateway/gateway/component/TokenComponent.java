package com.gateway.gateway.component;

import com.gateway.gateway.models.AccountCredentials;

public interface TokenComponent {
    AccountCredentials decodeToken(String token);

    String decodePassword(String password);

    String encodeEmail(String email);
}
