package com.gateway.gateway.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserFullModel {
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
    private String aboutUser;
    private String phone;
    private String role;
}
