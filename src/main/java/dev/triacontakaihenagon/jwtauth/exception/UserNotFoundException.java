package dev.triacontakaihenagon.jwtauth.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) { super(message);}
}
