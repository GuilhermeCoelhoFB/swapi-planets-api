package com.monnos.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends Exception{

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}

