package com.example.dayt.dayt.domain.auth.exception;

import com.example.dayt.dayt.global.error.exception.DayException;
import com.example.dayt.dayt.global.error.exception.ErrorCode;

public class RefreshTokenNotFoundException extends DayException {

    public static final DayException EXCEPTION = new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException(){
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
