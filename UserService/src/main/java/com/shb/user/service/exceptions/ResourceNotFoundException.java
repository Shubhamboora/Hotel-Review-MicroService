package com.shb.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource not found on database !!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
