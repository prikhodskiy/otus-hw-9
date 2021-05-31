package org.aprikhodskiy.otus.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String errorMessage){
        super(errorMessage);
    }
}