package com.example.springrest.exceptions;

import java.util.NoSuchElementException;

public class NoSuchVehicleIdFoundException extends Exception{
    public NoSuchVehicleIdFoundException(String msg){
        super(msg);
    }
}
