package com.musabeli.api_usuarios.exceptions;

public class UserEmailExistsException extends RuntimeException{
    public UserEmailExistsException(String message) {
        super(message);
    }
}
