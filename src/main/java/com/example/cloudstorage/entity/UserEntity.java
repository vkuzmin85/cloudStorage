package com.example.cloudstorage.entity;

import com.example.cloudstorage.jwt.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Table(name = "users")
@Entity
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Username shouldn't be empty")
    @Size(min = 2, max = 20, message = "Username should be between 2 and 20 characters")
    @Column(unique = true, name = "username")
    private String username;
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

}