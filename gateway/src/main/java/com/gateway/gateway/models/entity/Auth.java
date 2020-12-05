package com.gateway.gateway.models.entity;

import com.gateway.gateway.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(value = "auth")
public class Auth {
    @Id
    private String email;
    private String password;
    private Role roleAccess;
    private Boolean isActive;
}
