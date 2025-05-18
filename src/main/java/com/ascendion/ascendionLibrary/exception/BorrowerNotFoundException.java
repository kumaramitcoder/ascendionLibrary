package com.ascendion.ascendionLibrary.exception;

public class BorrowerNotFoundException extends RuntimeException{

    public BorrowerNotFoundException(String name){
        super(name);
    }
}
