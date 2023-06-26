package com.example.SriKart.ExceptionHandling;
public class UserIDNotFoundException extends RuntimeException{
    public UserIDNotFoundException(String message) {
        super(message);
    }
    public UserIDNotFoundException(Throwable cause, String message) {
        super(message, cause);
    }
}
