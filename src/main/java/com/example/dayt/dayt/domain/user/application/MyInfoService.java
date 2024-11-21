package com.example.dayt.dayt.domain.user.application;

import com.example.dayt.dayt.domain.user.application.facade.UserFaced;
import com.example.dayt.dayt.domain.user.dao.UserRepository;
import com.example.dayt.dayt.domain.user.domain.User;
import com.example.dayt.dayt.domain.user.dto.Response.MyInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyInfoService {

    private final UserRepository userRepository;

    private final UserFaced userFaced;

    @Transactional(readOnly = true)
    public MyInfoResponse myInfo(){

        User user = userFaced.currentUser();

        return MyInfoResponse.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .introduce(user.getIntroduce())
                .build();
    }
}
