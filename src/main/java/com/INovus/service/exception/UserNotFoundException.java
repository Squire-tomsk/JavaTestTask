package com.INovus.service.exception;

/**
 * Created by Alexander Abugaliev on 17.01.17.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
