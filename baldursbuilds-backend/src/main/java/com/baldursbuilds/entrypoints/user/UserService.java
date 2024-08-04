package com.baldursbuilds.entrypoints.user;

import com.baldursbuilds.security.PasswordService;
import com.baldursbuilds.security.annotations.HashPassword;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    @Inject
    PasswordService passwordService;

    @Transactional
    public User createUser(UserRequestPayload payload) {
        String salt = passwordService.generateSalt();
        String hashedPassword = passwordService.hashPassword(payload.password(), salt);

        User newUser = new User.UserBuilder()
                .username(payload.username())
                .password(hashedPassword)
                .email(payload.email())
                .salt(salt)
                .build();

        this.userRepository.persist(newUser);

        return newUser;
    }
}
