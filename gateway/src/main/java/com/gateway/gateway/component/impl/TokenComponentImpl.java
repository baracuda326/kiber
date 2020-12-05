package com.gateway.gateway.component.impl;

import com.gateway.gateway.component.TokenComponent;
import com.gateway.gateway.exceptions.RegistrationModelException;
import com.gateway.gateway.exceptions.TokenValidationException;
import com.gateway.gateway.models.AccountCredentials;
import org.springframework.stereotype.Component;

import java.util.Base64;

import static com.gateway.gateway.utils.Utils.*;

@Component
public class TokenComponentImpl implements TokenComponent {
    @Override
    public AccountCredentials decodeToken(String token) {
        try {
            int index = token.indexOf(" ");
            token = token.substring(index + 1);
            Base64.getDecoder().decode(token);
        } catch (IllegalArgumentException | IndexOutOfBoundsException | NullPointerException ex) {
            throw new TokenValidationException("Bad token");
        }
        byte[] base64DecodeBytes = Base64.getDecoder().decode(token);
        token = new String(base64DecodeBytes);
        String[] auth = token.split(":");
        if (auth.length > 2) throw new TokenValidationException("Length token no valid");
        if (isValidEmail(auth[0])) {
            throw new RegistrationModelException("No valid Email!");
        }
        if (isValidPassword(auth[1])) {
            throw new RegistrationModelException("No valid password");
        }
        return new AccountCredentials(auth[0].toLowerCase(), auth[1]);
    }

    @Override
    public String decodePassword(String password) {
        byte[] base64DecodeBytes = Base64.getDecoder().decode(password);
        password = new String(base64DecodeBytes);
        if (isValidPassword(password)) {
            throw new RegistrationModelException("No valid password");
        }
        return password;
    }

    @Override
    public String encodeEmail(String email) {
        return Base64.getEncoder().encodeToString(email.getBytes());
    }
}
