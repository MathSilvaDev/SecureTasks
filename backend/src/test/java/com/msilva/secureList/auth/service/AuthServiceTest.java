package com.msilva.secureList.auth.service;

import com.msilva.secureList.auth.dto.request.RegisterRequest;
import com.msilva.secureList.role.entity.Role;
import com.msilva.secureList.role.enums.RoleName;
import com.msilva.secureList.role.repository.RoleRepository;
import com.msilva.secureList.security.jwt.JwtService;
import com.msilva.secureList.user.entity.User;
import com.msilva.secureList.user.repository.UserRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Nested
    class Register{

        @Test
        void shouldRegisterWithSuccess(){

            RegisterRequest request = new RegisterRequest(
                    "test@test.test",
                    "username",
                    "rawPassword"
            );

            Role role = new Role(RoleName.BASIC);

            when(userRepository.existsByEmail(request.email()))
                    .thenReturn(false);

            when(roleRepository.findByName(role.getName()))
                    .thenReturn(Optional.of(role));

            when(passwordEncoder.encode(request.password()))
                    .thenReturn("hashedPassword");

            authService.register(request);

            verify(userRepository).save(argThat(user ->
                    user.getEmail().equals(request.email()) &&
                    user.getUsername().equals(request.username()) &&
                    user.getPassword().equals("hashedPassword")
            ));
        }
    }



}