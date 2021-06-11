package org.aprikhodskiy.otus.exceptions;

public class BookNotFoundException extends LibraryNotFoundException{
    public BookNotFoundException(String errorMessage){
        super(errorMessage);
    }
}