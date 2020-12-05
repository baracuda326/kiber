package com.gateway.gateway.repository;

import com.gateway.gateway.models.entity.Auth;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<Auth,String> {
}
