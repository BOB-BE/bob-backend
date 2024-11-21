package com.example.dayt.dayt.domain.user.exception;

public class NotAuthenticatedException extends RuntimeException{

    public NotAuthenticatedException(String message){
        super(message);
    }
}
