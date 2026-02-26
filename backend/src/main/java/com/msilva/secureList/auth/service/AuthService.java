package com.msilva.secureList.auth.service;

import com.msilva.secureList.auth.dto.request.RegisterRequest;
import com.msilva.secureList.role.entity.Role;
import com.msilva.secureList.role.enums.RoleValues;
import com.msilva.secureList.role.repository.RoleRepository;
import com.msilva.secureList.user.entity.User;
import com.msilva.secureList.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @Transactional
    public void register(RegisterRequest request){

        if(userRepository.existsByEmail(request.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Role roleBasic = roleRepository.findByName(RoleValues.BASIC.name()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        String hashPassword = passwordEncoder.encode(request.password());

        User user = new User(
                request.email(),
                request.username(),
                hashPassword,
                Set.of(roleBasic)
        );

        userRepository.save(user);
    }
}
