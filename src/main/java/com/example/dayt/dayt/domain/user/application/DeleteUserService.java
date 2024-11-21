package com.example.dayt.dayt.domain.user.application;

import com.example.dayt.dayt.domain.user.application.facade.UserFaced;
import com.example.dayt.dayt.domain.user.dao.UserRepository;
import com.example.dayt.dayt.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserService {

    private final UserRepository userRepository;

    private final UserFaced userFaced;

    @Transactional
    public void deleteUser(Long id){

        User user = userFaced.currentUser();

        userRepository.deleteById(id);
    }


}
