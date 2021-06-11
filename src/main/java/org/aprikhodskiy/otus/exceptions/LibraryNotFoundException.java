package org.aprikhodskiy.otus.exceptions;

public class LibraryNotFoundException extends RuntimeException{
    public LibraryNotFoundException(String errorMessage){
        super(errorMessage);
    }
}