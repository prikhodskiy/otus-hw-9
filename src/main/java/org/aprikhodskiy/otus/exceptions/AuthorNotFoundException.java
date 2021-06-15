package org.aprikhodskiy.otus.exceptions;

public class AuthorNotFoundException extends LibraryNotFoundException{
    public AuthorNotFoundException(String errorMessage){
        super(errorMessage);
    }
}