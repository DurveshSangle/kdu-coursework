package com.kdu.smarthome.exceptions.customexceptions;

public class InvalidCredentialForDeviceException extends Exception{
    public InvalidCredentialForDeviceException(String message) {
        super(message);
    }
}
