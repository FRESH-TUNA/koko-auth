package com.koko.auth.domains.user;

import com.koko.auth.domains.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")})
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity {
    /*
     * columns
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    /*
     * fields
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    /*
     * methods
     */
    public String getRoleKey() {
        return this.role.getKey();
    }

    public User update(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        return this;
    }

    @Builder
    public User(String name, String email,
                String imageUrl, Role role,
                boolean emailVerified,
                AuthProvider provider,
                String providerId) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.role = role;
        this.emailVerified = emailVerified;
        this.provider = provider;
        this.providerId = providerId;
    }
}


//https://minholee93.tistory.com/entry/Spring-Security-Google-Login-with-Spring-Security-JWT-1
//https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-1/