package com.example.dayt.dayt.domain.auth.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class LogoutRequest {

    private String accessToken;

}
