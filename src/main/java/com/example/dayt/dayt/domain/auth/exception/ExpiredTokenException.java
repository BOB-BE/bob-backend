package com.example.dayt.dayt.domain.auth.exception;

import com.example.dayt.dayt.global.error.exception.DayException;
import com.example.dayt.dayt.global.error.exception.ErrorCode;

public class ExpiredTokenException extends DayException {

    public static final DayException EXCEPTION = new ExpiredTokenException();

    public ExpiredTokenException(){
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
