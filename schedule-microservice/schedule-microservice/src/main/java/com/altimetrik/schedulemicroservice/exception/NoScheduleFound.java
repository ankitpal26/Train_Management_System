package com.altimetrik.schedulemicroservice.exception;

public class NoScheduleFound extends RuntimeException {
    public NoScheduleFound() {
        super("No Schedule found !!");
    }

    public NoScheduleFound(String message) {
        super(message);
    }
}
