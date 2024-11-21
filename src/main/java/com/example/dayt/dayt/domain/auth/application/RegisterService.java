package com.example.dayt.dayt.domain.auth.application;

import com.example.dayt.dayt.domain.auth.dto.request.RegisterRequest;
import com.example.dayt.dayt.domain.auth.exception.UserAlreadyTaken;
import com.example.dayt.dayt.domain.user.dao.UserRepository;
import com.example.dayt.dayt.domain.user.domain.User;
import com.example.dayt.dayt.global.security.auth.AuthDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthDetailsService authDetailsService;


    public void register(RegisterRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw UserAlreadyTaken.EXCEPTION;
        }

        userRepository.save(
                User.builder()
                        .email(request.getEmail())
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .name(request.getName())
                        .introduce(request.getIntroduce())
                        .build());

    }
}
