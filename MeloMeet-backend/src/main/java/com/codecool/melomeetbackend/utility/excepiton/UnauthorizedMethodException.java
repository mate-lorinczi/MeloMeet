package com.codecool.melomeetbackend.utility.excepiton;

public class UnauthorizedMethodException extends RuntimeException{

    public UnauthorizedMethodException(String message) {
        super(message);
    }
}
