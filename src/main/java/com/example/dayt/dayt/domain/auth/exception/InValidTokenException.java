package com.example.dayt.dayt.domain.auth.exception;

import com.example.dayt.dayt.global.error.exception.DayException;
import com.example.dayt.dayt.global.error.exception.ErrorCode;

public class InValidTokenException extends DayException {

    public static final DayException EXCEPTION = new InValidTokenException();

    public InValidTokenException(){
        super(ErrorCode.INVALID_TOKEN);
    }
}
