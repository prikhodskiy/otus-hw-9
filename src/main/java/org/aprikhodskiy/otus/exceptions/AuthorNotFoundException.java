package org.aprikhodskiy.otus.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(String errorMessage){
        super(errorMessage);
    }
}