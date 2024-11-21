package com.example.dayt.dayt.domain.auth.application;

import com.example.dayt.dayt.domain.auth.dao.RefreshTokenRepository;
import com.example.dayt.dayt.domain.auth.dto.request.LogoutRequest;
import com.example.dayt.dayt.domain.user.dao.UserRepository;
import com.example.dayt.dayt.global.security.jwt.JwtTokenProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class LogoutService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, String> redisTemplate;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void logout(LogoutRequest logoutRequest) {

        String accessToken = logoutRequest.getAccessToken();

        // 토큰에서 사용자명 추출
        String username = jwtTokenProvider.getSubjectFromToken(accessToken);

        // Redis에서 해당 사용자의 리프레시 토큰 삭제
        refreshTokenRepository.deleteById(username);

        // Access token 무효화 처리 (Redis 블랙리스트에 추가)
        long expiration = jwtTokenProvider.getExpiration(accessToken);
        redisTemplate.opsForValue().set(accessToken, "logout", expiration, TimeUnit.MILLISECONDS);
    }
}
