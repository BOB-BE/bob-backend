package com.example.dayt.dayt.domain.auth.application;

import com.example.dayt.dayt.domain.auth.dao.RefreshTokenRepository;
import com.example.dayt.dayt.domain.auth.domain.RefreshToken;
import com.example.dayt.dayt.domain.auth.dto.request.RefreshTokenRequest;
import com.example.dayt.dayt.domain.auth.dto.response.LoginResponse;
import com.example.dayt.dayt.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.dayt.dayt.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueService {

    private final JwtTokenProvider jwtTokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public LoginResponse reissue(RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        return jwtTokenProvider.receiveToken(refreshToken.getUsername());
    }
}