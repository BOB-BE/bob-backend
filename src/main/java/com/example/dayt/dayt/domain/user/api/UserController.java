package com.example.dayt.dayt.domain.user.api;

import com.example.dayt.dayt.domain.user.application.DeleteUserService;
import com.example.dayt.dayt.domain.user.application.MyInfoService;
import com.example.dayt.dayt.domain.user.application.UpdateMyInfoService;
import com.example.dayt.dayt.domain.user.dto.Request.UpdateMyInfoRequest;
import com.example.dayt.dayt.domain.user.dto.Response.MyInfoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final MyInfoService myInfoService;

    private final UpdateMyInfoService updateMyInfoService;

    private final DeleteUserService deleteUserService;

    @GetMapping("/my-info")
    public MyInfoResponse myInfo(){
        return myInfoService.myInfo();
    }

    @PatchMapping("/update")
    public void MyInfoUpdate(@RequestBody @Valid UpdateMyInfoRequest request){
        updateMyInfoService.updateMyInfo(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        deleteUserService.deleteUser(id);
    }




}
