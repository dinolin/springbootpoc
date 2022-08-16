package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;

public enum UserAuthorityModel {
    ADMIN, NORMAL;

    @JsonCreator
    public UserAuthorityModel fromString(String key) {
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(key))
                .findFirst()
                .orElse(null);
    }
}
