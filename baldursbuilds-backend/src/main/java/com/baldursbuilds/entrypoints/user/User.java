package com.baldursbuilds.entrypoints.user;

import com.baldursbuilds.security.UserRole;
import com.baldursbuilds.security.annotations.HashPassword;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Nonnull
    @Column(nullable = false)
    private String username;

    @Nonnull
    @Column(nullable = false)
    private String password;

    @Nonnull
    @Column(nullable = false)
    private String salt;

    @Column(length = 100, nullable = false)
    private String email;

    @Nonnull
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Nonnull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public User(@Nonnull String username, @Nonnull String password, String email, @Nonnull String salt) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.salt = salt;
        this.role = UserRole.EDITOR;
        this.createdAt = LocalDateTime.now();
    }
}
