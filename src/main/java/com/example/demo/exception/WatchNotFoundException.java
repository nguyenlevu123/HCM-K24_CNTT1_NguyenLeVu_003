package com.example.demo.exception;

public class WatchNotFoundException extends RuntimeException{

    public WatchNotFoundException(String message){
        super(message);
    }
}
