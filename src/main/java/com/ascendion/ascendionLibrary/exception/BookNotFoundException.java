package com.ascendion.ascendionLibrary.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String name){
        super(name);
    }
}
