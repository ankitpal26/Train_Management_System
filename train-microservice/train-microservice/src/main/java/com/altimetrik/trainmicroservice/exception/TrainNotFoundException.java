package com.altimetrik.trainmicroservice.exception;

public class TrainNotFoundException extends RuntimeException {
    public TrainNotFoundException(){
        super("Train not found !!");
    }

   public   TrainNotFoundException(String s){
        super(s);
    }
}
