package com.example.dayt.dayt.domain.user.application;

import com.example.dayt.dayt.domain.auth.exception.PasswordMismatchException;
import com.example.dayt.dayt.domain.user.application.facade.UserFaced;
import com.example.dayt.dayt.domain.user.dao.UserRepository;
import com.example.dayt.dayt.domain.user.domain.User;
import com.example.dayt.dayt.domain.user.dto.Request.UpdateMyInfoRequest;
import com.example.dayt.dayt.domain.user.dto.Response.MyInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMyInfoService {

    private final UserRepository userRepository;

    private final UserFaced userFaced;

    private final PasswordEncoder passwordEncoder;

    public void updateMyInfo(UpdateMyInfoRequest request){

        User user = userFaced.currentUser();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw PasswordMismatchException.EXCEPTION;

        user.updateMyInfo(
                request.getEmail(),
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getName(),
                request.getIntroduce());

    }


}
