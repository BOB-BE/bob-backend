package com.example.dayt.dayt.domain.user.exception;

import com.example.dayt.dayt.global.error.exception.DayException;
import com.example.dayt.dayt.global.error.exception.ErrorCode;

public class UserNotFoundException extends DayException {

    public static final DayException EXCEPTION = new UserNotFoundException();

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}