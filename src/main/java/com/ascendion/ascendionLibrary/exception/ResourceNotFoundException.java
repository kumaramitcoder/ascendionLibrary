package com.ascendion.ascendionLibrary.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String msg){
        super((msg));
    }

}
