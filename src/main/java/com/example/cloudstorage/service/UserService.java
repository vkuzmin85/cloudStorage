package com.example.cloudstorage.service;

import com.example.cloudstorage.entity.UserEntity;
import com.example.cloudstorage.jwt.AuthUser;
import com.example.cloudstorage.jwt.Role;
import com.example.cloudstorage.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        PasswordEncoder enc = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
        this.userRepository.saveAll(List.of(
                new UserEntity("user", enc.encode("123"), Role.ROLE_USER),
                new UserEntity("user2", enc.encode("1234"), Role.ROLE_USER))
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(AuthUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username" + username + "not found"));
    }
}
