package com.example.springrest.exceptions;

public class ArgumentInvalidException extends Exception{
    public ArgumentInvalidException(String msg){
        super(msg);
    }
}
