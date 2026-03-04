package com.msilva.secureList.auth.service;

import com.msilva.secureList.auth.dto.request.LoginRequest;
import com.msilva.secureList.auth.dto.request.RegisterRequest;
import com.msilva.secureList.auth.dto.response.LoginResponse;
import com.msilva.secureList.role.entity.Role;
import com.msilva.secureList.role.enums.RoleName;
import com.msilva.secureList.role.repository.RoleRepository;
import com.msilva.secureList.security.authentication.CustomUserDetails;
import com.msilva.secureList.security.jwt.JwtService;
import com.msilva.secureList.security.jwt.dto.TokenData;
import com.msilva.secureList.user.entity.User;
import com.msilva.secureList.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public void register(RegisterRequest request){

        if(userRepository.existsByEmail(request.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Role roleBasic = roleRepository.findByName(RoleName.BASIC).
                orElseThrow(() -> new IllegalStateException("Default role BASIC not found"));

        String hashPassword = passwordEncoder.encode(request.password());

        User user = new User(
                request.email(),
                request.username(),
                hashPassword
        );

        user.addRole(roleBasic);

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        TokenData tokenData = jwtService.generateToken(
                (CustomUserDetails) authentication.getPrincipal()
        );

        return new LoginResponse(tokenData.token(), tokenData.ExpiresAt());
    }
}
