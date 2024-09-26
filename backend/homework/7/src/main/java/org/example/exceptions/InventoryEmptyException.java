package org.example.exceptions;

public class InventoryEmptyException extends Exception{
    public InventoryEmptyException(String msg){
        super(msg);
    }
}
