package com.example.dayt.dayt.domain.user.exception;

import com.example.dayt.dayt.global.error.exception.DayException;
import com.example.dayt.dayt.global.error.exception.ErrorCode;

public class UserMismatchException extends DayException {

    public static final DayException EXCEPTION = new UserMismatchException();

    public UserMismatchException(){
        super(ErrorCode.USER_MISMATCH);
    }
}
