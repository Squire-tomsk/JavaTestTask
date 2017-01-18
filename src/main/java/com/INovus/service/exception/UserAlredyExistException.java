package com.INovus.service.exception;

/**
 * Created by Alexander Abugaliev on 17.01.17.
 */
public class UserAlredyExistException extends RuntimeException {
    public UserAlredyExistException(String message) {
        super(message);
    }
}
