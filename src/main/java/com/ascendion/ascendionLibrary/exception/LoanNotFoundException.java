package com.ascendion.ascendionLibrary.exception;

public class LoanNotFoundException extends RuntimeException{

    public LoanNotFoundException(String name){
        super(name);
    }
}
