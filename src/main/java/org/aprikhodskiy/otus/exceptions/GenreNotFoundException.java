package org.aprikhodskiy.otus.exceptions;

public class GenreNotFoundException extends LibraryNotFoundException{
    public GenreNotFoundException(String errorMessage){
        super(errorMessage);
    }
}