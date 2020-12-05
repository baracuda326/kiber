package com.gateway.gateway.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthModel {
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
