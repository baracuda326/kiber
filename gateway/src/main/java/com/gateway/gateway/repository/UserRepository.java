package com.gateway.gateway.repository;

import com.gateway.gateway.models.dto.AuthModel;
import com.gateway.gateway.models.dto.UserFullModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "userservice")
public interface UserRepository {
    @PostMapping(value = "/user/registration")
    UserFullModel registrationUser(@RequestBody AuthModel authModel);
}
