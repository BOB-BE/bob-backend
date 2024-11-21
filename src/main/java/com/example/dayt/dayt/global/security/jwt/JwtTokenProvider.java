package com.example.dayt.dayt.global.security.jwt;


import com.example.dayt.dayt.domain.auth.dao.RefreshTokenRepository;
import com.example.dayt.dayt.domain.auth.domain.RefreshToken;
import com.example.dayt.dayt.domain.auth.dto.response.LoginResponse;
import com.example.dayt.dayt.domain.auth.exception.ExpiredTokenException;
import com.example.dayt.dayt.domain.auth.exception.InValidTokenException;
import com.example.dayt.dayt.domain.user.dao.UserRepository;
import com.example.dayt.dayt.domain.user.domain.User;
import com.example.dayt.dayt.domain.user.exception.UserNotFoundException;
import com.example.dayt.dayt.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private final UserRepository userRepository;

    private final AuthDetailsService authDetailsService;

    private final RefreshTokenRepository refreshTokenRepository;

    // access token 생성
    public String createAccessToken(String username) {

        Date now = new Date();

        return Jwts.builder()
                .setSubject(username)
                .claim("type", "access")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getAccessExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();
    }

    public String createRefreshToken(String username) {

        Date now = new Date();

        String refreshToken = Jwts.builder()
                .claim("type", "refresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getRefreshExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .username(username)
                        .token(refreshToken)
                        .timeToLive((jwtProperties.getRefreshExpiration()))
                        .build());

        return refreshToken;
    }

    // 토큰에 담겨있는 userId로 SpringSecurity Authentication 정보를 반환하는 메서드
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        UserDetails userDetails = authDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Claims getClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InValidTokenException.EXCEPTION;
        }
    }

        public LoginResponse receiveToken(String username){

            Date now = new Date();

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> UserNotFoundException.EXCEPTION);

            return LoginResponse
                    .builder()
                    .accessToken(createAccessToken(username))
                    .refreshToken(createRefreshToken(username))
                    .accessExpiredAt(new Date(now.getTime() + jwtProperties.getAccessExpiration()))
                    .refreshExpiredAt(new Date(now.getTime() + jwtProperties.getRefreshExpiration()))
                    .build();
        }

        // HTTP 요청 헤더에서 토큰을 가져오는 메서드
        public String resolveToken (HttpServletRequest request){
            String bearerToken = request.getHeader(jwtProperties.getHeader());

            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.getPrefix())
                    && bearerToken.length() > jwtProperties.getPrefix().length() + 1) {
                return bearerToken.substring(7);
            }
            return null;
        }

    public long getExpiration(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().getTime() - new Date().getTime();
    }
    public String getSubjectFromToken(String token) {
        return getClaims(token).getSubject();
    }

    }
