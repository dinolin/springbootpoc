package com.example.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.example.model.AppUserModel;
import com.example.model.AppUserRequest;
import com.example.model.AppUserResponse;

public class AppUserConverter {

    private AppUserConverter() {

    }

    public static AppUserModel toAppUser(AppUserRequest request) {
        AppUserModel user = new AppUserModel();
        user.setEmailAddress(request.getEmailAddress());
        user.setPassword(request.getPassword());
        user.setName(request.getName());
        user.setAuthorities(request.getAuthorities());

        return user;
    }

    public static AppUserResponse toAppUserResponse(AppUserModel user) {
        AppUserResponse response = new AppUserResponse();
        response.setId(user.getId());
        response.setEmailAddress(user.getEmailAddress());
        response.setName(user.getName());
        response.setAuthorities(user.getAuthorities());

        return response;
    }

    public static List<AppUserResponse> toAppUserResponses(List<AppUserModel> users) {
        return users.stream()
                .map(AppUserConverter::toAppUserResponse)
                .collect(Collectors.toList());
    }
}
