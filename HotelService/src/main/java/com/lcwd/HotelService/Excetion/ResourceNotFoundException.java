package com.lcwd.HotelService.Excetion;

public class ResourceNotFoundException extends  RuntimeException{

    public  ResourceNotFoundException(String s){
        super(s);
    }

    public ResourceNotFoundException(){
        super("Resource not Found on server !! ");
    }
}
