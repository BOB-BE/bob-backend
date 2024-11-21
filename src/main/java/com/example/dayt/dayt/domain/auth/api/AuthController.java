package com.example.dayt.dayt.domain.auth.api;

import com.example.dayt.dayt.domain.auth.application.LoginService;

import com.example.dayt.dayt.domain.auth.application.LogoutService;
import com.example.dayt.dayt.domain.auth.application.RegisterService;
import com.example.dayt.dayt.domain.auth.application.ReissueService;
import com.example.dayt.dayt.domain.auth.dto.request.LoginRequest;
import com.example.dayt.dayt.domain.auth.dto.request.RefreshTokenRequest;
import com.example.dayt.dayt.domain.auth.dto.request.RegisterRequest;
import com.example.dayt.dayt.domain.auth.dto.response.LoginResponse;
import com.example.dayt.dayt.domain.auth.dto.request.LogoutRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;

    private final RegisterService registerService;

    private final ReissueService reissueService;

    private final LogoutService logoutService;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.login(request);
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequest request) {
        registerService.register(request);
    }

    @PatchMapping("/reissue")
    public LoginResponse reissue(@RequestBody @Valid RefreshTokenRequest request) {
        return reissueService.reissue(request);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody LogoutRequest logoutRequest) {
        logoutService.logout(logoutRequest);
    }
}
