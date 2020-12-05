package com.gateway.gateway.service.impl;

import com.gateway.gateway.models.MyUserDetails;
import com.gateway.gateway.models.entity.Auth;
import com.gateway.gateway.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final AuthRepository authRepository;

    @Autowired
    public UserDetailServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Auth user = authRepository.findById(email.toLowerCase()).filter(Auth::getIsActive).orElseThrow(() -> new UsernameNotFoundException("User not exist!"));
        return new MyUserDetails(user);
    }
}
