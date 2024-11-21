package com.example.dayt.dayt.domain.user.exception;

import com.example.dayt.dayt.global.error.exception.DayException;
import com.example.dayt.dayt.global.error.exception.ErrorCode;

public class InvalidUserException extends DayException {

    public static final DayException EXCEPTION = new InvalidUserException();

    public InvalidUserException() {
        super(ErrorCode.INVALID_USER);
    }
}
