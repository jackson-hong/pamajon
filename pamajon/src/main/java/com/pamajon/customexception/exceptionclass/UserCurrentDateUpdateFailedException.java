package com.pamajon.customexception.exceptionclass;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserCurrentDateUpdateFailedException extends RuntimeException{

    public UserCurrentDateUpdateFailedException(String message){
        super(message);
    }
}
