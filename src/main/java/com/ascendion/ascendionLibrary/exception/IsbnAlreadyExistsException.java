package com.ascendion.ascendionLibrary.exception;

public class IsbnAlreadyExistsException extends RuntimeException{

    public IsbnAlreadyExistsException(String name){
        super(name);
    }

}
