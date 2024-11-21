package com.example.dayt.dayt.domain.user.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MyInfoResponse {

    private String username;

    private String password;

    private String name;

    private String introduce;
}
