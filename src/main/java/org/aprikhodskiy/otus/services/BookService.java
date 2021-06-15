package org.aprikhodskiy.otus.services;

import lombok.AllArgsConstructor;
import org.aprikhodskiy.otus.dto.BookCreateDto;
import org.aprikhodskiy.otus.dto.BookDetailDto;
import org.aprikhodskiy.otus.dto.BookDto;
import org.aprikhodskiy.otus.dto.ModelMapper;
import org.aprikhodskiy.otus.exceptions.AuthorNotFoundException;
import org.aprikhodskiy.otus.exceptions.BookNotFoundException;
import org.aprikhodskiy.otus.exceptions.GenreNotFoundException;
import org.aprikhodskiy.otus.models.Author;
import org.aprikhodskiy.otus.models.Book;
import org.aprikhodskiy.otus.models.Genre;
import org.aprikhodskiy.otus.repositories.AuthorRepository;
import org.aprikhodskiy.otus.repositories.BookRepository;
import org.aprikhodskiy.otus.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final ResourceService resourceService;

    private Book getBookOrThrow(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(resourceService.localize("book-not-found", id)));
    }

    @Transactional(readOnly = true)
    public List<BookDto> findAllBooks() {
        return bookRepository.findAll()
                .stream().map(ModelMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookDetailDto fineOneBook(long bookId) {
        return ModelMapper.toDetailDto(getBookOrThrow(bookId));
    }

    public void deleteBook(Long bookId) {
        bookRepository.delete(getBookOrThrow(bookId));
    }

    @Transactional
    public BookDetailDto bookUpdate(Long bookId, BookCreateDto newBook) {
        var book = getBookOrThrow(bookId);
        book.setName(newBook.getName());
        book.setGenre(getGenreOrThrow(newBook.getGenreId()));
        book.setAuthor(getAuthorOrThrow(newBook.getAuthorId()));

        var updatedBook = bookRepository.save(book);

        return ModelMapper.toDetailDto(updatedBook);
    }

    private Genre getGenreOrThrow(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(resourceService.localize("genre-not-found", id)));
    }

    private Author getAuthorOrThrow(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(resourceService.localize("author-not-found", id)));
    }

    @Transactional
    public BookDetailDto bookCreate(BookCreateDto newBook) {
        var book = Book.builder()
                .name(newBook.getName())
                .genre(getGenreOrThrow(newBook.getGenreId()))
                .author(getAuthorOrThrow(newBook.getAuthorId()))
                .build();

        var createdBook = bookRepository.save(book);

        return ModelMapper.toDetailDto(createdBook);
    }
}
