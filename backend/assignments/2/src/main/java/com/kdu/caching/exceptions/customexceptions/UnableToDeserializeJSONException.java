package com.kdu.caching.exceptions.customexceptions;

public class UnableToDeserializeJSONException extends RuntimeException{
    public UnableToDeserializeJSONException(String msg,Exception e){
        super(msg,e);
    }
}
