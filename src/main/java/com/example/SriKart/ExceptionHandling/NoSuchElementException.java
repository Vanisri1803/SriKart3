package com.example.SriKart.ExceptionHandling;

public class NoSuchElementException extends RuntimeException{

    public NoSuchElementException(String message) {
        super(message);
    }
    public NoSuchElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
