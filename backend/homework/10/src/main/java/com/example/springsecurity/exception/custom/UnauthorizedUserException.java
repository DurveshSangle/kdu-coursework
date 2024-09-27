package com.example.springsecurity.exception.custom;

public class UnauthorizedUserException extends Exception{
    public UnauthorizedUserException(String msg){
        super(msg);
    }
}
