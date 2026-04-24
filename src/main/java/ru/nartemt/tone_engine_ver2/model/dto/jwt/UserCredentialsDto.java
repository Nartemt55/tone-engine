package ru.nartemt.tone_engine_ver2.model.dto.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserCredentialsDto(
        @JsonProperty(namespace = "username") String name,
        String password) {
}
