package com.example.dayt.dayt.domain.auth.dto.request;

import com.example.dayt.dayt.domain.user.domain.enums.Role;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequest {

    private String email;

    private String username;


    private String password;

    @Column(length = 4)
    private String name;

    @Column(length = 255)
    private String introduce;

}
