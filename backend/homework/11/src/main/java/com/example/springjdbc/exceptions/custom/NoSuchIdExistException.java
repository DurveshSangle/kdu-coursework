package com.example.springjdbc.exceptions.custom;
public class NoSuchIdExistException extends Exception{
    public NoSuchIdExistException(String message) {
        super(message);
    }
}
