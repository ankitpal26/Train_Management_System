package com.altimetrik.routemicroservice.exception;

public class RouteNotFoundException extends RuntimeException {
    public RouteNotFoundException() {
        super("Route not found !!");
    }

    public RouteNotFoundException(String s) {
        super(s);
    }
}
