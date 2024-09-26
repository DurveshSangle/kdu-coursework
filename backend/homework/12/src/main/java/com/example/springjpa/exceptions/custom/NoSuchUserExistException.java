package com.example.springjpa.exceptions.custom;

public class NoSuchUserExistException extends Exception{
    public NoSuchUserExistException(String message) {
        super(message);
    }
}
