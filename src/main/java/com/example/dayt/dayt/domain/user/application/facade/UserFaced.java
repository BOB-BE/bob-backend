package com.example.dayt.dayt.domain.user.application.facade;

import com.example.dayt.dayt.domain.user.dao.UserRepository;
import com.example.dayt.dayt.domain.user.domain.User;
import com.example.dayt.dayt.domain.user.exception.NotAuthenticatedException;
import com.example.dayt.dayt.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFaced {

    private final UserRepository userRepository;

    public User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new NotAuthenticatedException("인증되지 않는 유저입니다.");
        }

        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}
