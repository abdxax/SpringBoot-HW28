package com.example.springboothw28.handling;

public class ApiException extends RuntimeException{
    public ApiException(String msg){
        super(msg);
    }
}
