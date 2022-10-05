package com.example.pratica01spring04.exception;

public class BlogAlreadyExistsException extends RuntimeException{
    public BlogAlreadyExistsException(String message) {
        super(message);
    }
}
