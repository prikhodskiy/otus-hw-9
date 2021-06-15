package org.aprikhodskiy.otus.controllers;

import lombok.AllArgsConstructor;
import org.aprikhodskiy.otus.dto.BookCreateDto;
import org.aprikhodskiy.otus.dto.BookDetailDto;
import org.aprikhodskiy.otus.dto.BookDto;
import org.aprikhodskiy.otus.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDetailDto> findOneBookById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                bookService.fineOneBook(id)
        );
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteOneBookById(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/books")
    public ResponseEntity<BookDetailDto> bookCreate( @RequestBody BookCreateDto newBook){
        return ResponseEntity.status(HttpStatus.OK).body(
                bookService.bookCreate(newBook)
        );
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDetailDto> bookUpdate(@PathVariable("id") Long id, @RequestBody BookCreateDto newBook){
        return ResponseEntity.status(HttpStatus.OK).body(
                bookService.bookUpdate(id, newBook)
        );
    }

    @RequestMapping("/books")
    public ResponseEntity<List<BookDto>> books() {
        return ResponseEntity.status(HttpStatus.OK).body(
                bookService.findAllBooks()
        );
    }
}
