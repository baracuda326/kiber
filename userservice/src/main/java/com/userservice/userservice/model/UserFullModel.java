package com.userservice.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserFullModel {
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
    private String aboutUser;
    private String phone;
    private String role;
}
