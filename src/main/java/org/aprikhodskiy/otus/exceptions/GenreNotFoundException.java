package org.aprikhodskiy.otus.exceptions;

public class GenreNotFoundException extends RuntimeException{
    public GenreNotFoundException(String errorMessage){
        super(errorMessage);
    }
}