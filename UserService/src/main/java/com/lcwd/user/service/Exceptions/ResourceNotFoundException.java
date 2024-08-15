package com.lcwd.user.service.Exceptions;

public class ResourceNotFoundException extends  RuntimeException{

    public  ResourceNotFoundException(){
        super("Resource Not found on the server");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
