package com.app.store.utils.exceptions;

public class CustomUniqueConstraintViolationException extends Exception{
    public CustomUniqueConstraintViolationException(String message) {
        super(message);
    }

}
