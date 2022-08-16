package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.AppUserModel;


import java.util.Optional;

public interface AppUserRepository extends MongoRepository<AppUserModel, String> {

    Optional<AppUserModel> findByEmailAddress(String email);

}