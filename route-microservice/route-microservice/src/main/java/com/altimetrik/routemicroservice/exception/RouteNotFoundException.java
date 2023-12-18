package com.altimetrik.routemicroservice.exception;

public class RouteNotFoundException extends RuntimeException {
    public RouteNotFoundException(){
        super("Train not found !!");
    }

   public   RouteNotFoundException(String s){
        super(s);
    }
}
