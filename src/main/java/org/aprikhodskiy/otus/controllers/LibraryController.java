package org.aprikhodskiy.otus.controllers;

import lombok.AllArgsConstructor;
import org.aprikhodskiy.otus.dto.*;
import org.aprikhodskiy.otus.exceptions.AuthorNotFoundException;
import org.aprikhodskiy.otus.exceptions.BookNotFoundException;
import org.aprikhodskiy.otus.exceptions.GenreNotFoundException;
import org.aprikhodskiy.otus.services.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class LibraryController {

    private final LibraryService libraryService;

    @RequestMapping("/authors")
    public ResponseEntity<List<AuthorDto>> authors() {
        return ResponseEntity.status(HttpStatus.OK).body(
                libraryService.findAllAuthors()
        );
    }

    @RequestMapping("/genres")
    public ResponseEntity<List<GenreDto>> genres() {
        return ResponseEntity.status(HttpStatus.OK).body(
                libraryService.findAllGenres()
        );
    }

    @RequestMapping("/books")
    public ResponseEntity<List<BookDto>> books() {
        return ResponseEntity.status(HttpStatus.OK).body(
                libraryService.findAllBooks()
        );
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDetailDto> findOneBookById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                libraryService.fineOneBook(id)
        );
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteOneBookById(@PathVariable("id") Long id) {
        libraryService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/books")
    public ResponseEntity<BookDetailDto> bookCreate( @RequestBody BookCreateDto newBook){
        return ResponseEntity.status(HttpStatus.OK).body(
                libraryService.bookCreate(newBook)
        );
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDetailDto> bookUpdate(@PathVariable("id") Long id, @RequestBody BookCreateDto newBook){
        return ResponseEntity.status(HttpStatus.OK).body(
                libraryService.bookUpdate(id, newBook)
        );
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleNotFound(BookNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<String> handleNotFound(GenreNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<String> handleNotFound(AuthorNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
