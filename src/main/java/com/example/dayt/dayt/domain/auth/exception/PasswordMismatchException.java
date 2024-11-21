package com.example.dayt.dayt.domain.auth.exception;

import com.example.dayt.dayt.global.error.exception.DayException;
import com.example.dayt.dayt.global.error.exception.ErrorCode;

public class PasswordMismatchException extends DayException {

    public static final DayException EXCEPTION = new PasswordMismatchException();

    public PasswordMismatchException(){
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
