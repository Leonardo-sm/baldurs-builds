package com.baldursbuilds.security;

import com.baldursbuilds.security.utils.HashUtils;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordService {
    public String hashPassword(String rawPassword, String salt) {
        return HashUtils.encryptToSha256(rawPassword, salt);
    }

    public  String generateSalt() {
        return HashUtils.generateSalt();
    }

    public boolean verifyPassword(String rawPassword, String hashedPassword, String salt) {
        return HashUtils.compareHash(rawPassword, hashedPassword, salt);

    }
}
