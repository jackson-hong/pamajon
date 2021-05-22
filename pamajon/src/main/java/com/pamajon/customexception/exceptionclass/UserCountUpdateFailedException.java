package com.pamajon.customexception.exceptionclass;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserCountUpdateFailedException extends RuntimeException {

    public UserCountUpdateFailedException(String message){
        super(message);
    }
}
