package com.baldursbuilds.entrypoints.user;

public record UserRequestPayload(String username, String password, String email) {
}
