package com.msilva.secureList.user.entity;

import com.msilva.secureList.role.entity.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private Instant createdAt;

    @ManyToMany
    @JoinTable(
            name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User(String email, String username,
                String password, Set<Role> roles){

        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
