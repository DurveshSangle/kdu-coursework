package com.example.springjdbc.exceptions.custom;

public class FailedTransactionRollBackException extends Exception{
    public FailedTransactionRollBackException(String message) {
        super(message);
    }
}
