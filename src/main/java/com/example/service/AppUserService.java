package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.converter.AppUserConverter;
import com.example.exception.NotFoundException;
import com.example.exception.UnprocessableEntityException;
import com.example.model.AppUserModel;
import com.example.model.AppUserRequest;
import com.example.model.AppUserResponse;
import com.example.repository.AppUserRepository;

public class AppUserService {

    private AppUserRepository repository;

    public AppUserService(AppUserRepository repository) {
        this.repository = repository;
    }

    public AppUserResponse createUser(AppUserRequest request) {
        Optional<AppUserModel> existingUser = repository.findByEmailAddress(request.getEmailAddress());
        if (existingUser.isPresent()) {
            throw new UnprocessableEntityException("This email address has been used.");
        }

        AppUserModel user = AppUserConverter.toAppUser(request);
        user = repository.insert(user);
        return AppUserConverter.toAppUserResponse(user);
    }

    public AppUserResponse getUserResponseById(String id) {
        AppUserModel user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find user."));

        return AppUserConverter.toAppUserResponse(user);
    }

    public AppUserModel getUserByEmail(String email) {
        return repository.findByEmailAddress(email)
                .orElseThrow(() -> new NotFoundException("Can't find user."));
    }

    public List<AppUserResponse> getUserResponses() {
        List<AppUserModel> users = repository.findAll();
        return AppUserConverter.toAppUserResponses(users);
    }

}
