package com.kdu.smarthome.exceptions.customexceptions;

public class OnlyAdminAllowedException extends Exception{
    public OnlyAdminAllowedException(String message) {
        super(message);
    }
}
