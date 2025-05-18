package com.ascendion.ascendionLibrary.exception;

public class BorrowerAllreadyExistsException extends RuntimeException{

    public BorrowerAllreadyExistsException(String name){
        super(name);
    }
}
