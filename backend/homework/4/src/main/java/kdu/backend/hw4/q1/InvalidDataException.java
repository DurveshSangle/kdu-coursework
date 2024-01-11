package kdu.backend.hw4.q1;
public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String msg,Throwable cause){
        super(msg,cause);
    }
}
