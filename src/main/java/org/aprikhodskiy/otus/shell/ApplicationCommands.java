package org.aprikhodskiy.otus.shell;

import lombok.RequiredArgsConstructor;
import org.aprikhodskiy.otus.exceptions.BookNotFoundException;
import org.aprikhodskiy.otus.models.Book;
import org.aprikhodskiy.otus.repositories.AuthorRepositoryJpaImpl;
import org.aprikhodskiy.otus.repositories.BookRepositoryJpaImpl;
import org.aprikhodskiy.otus.repositories.CommentRepositoryJpaImpl;
import org.aprikhodskiy.otus.repositories.GenreRepositoryJpaImpl;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;


@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final AuthorRepositoryJpaImpl authorRepository;
    private final GenreRepositoryJpaImpl genreRepository;
    private final BookRepositoryJpaImpl bookRepository;
    private final CommentRepositoryJpaImpl commentRepository;


    @Transactional(readOnly = true)
    @ShellMethod(value = "View authors", key = {"a", "authors"})
    public void listAuthors() {
        System.out.println("Список авторов");
        authorRepository.findAll().forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "View genres", key = {"g", "genres"})
    public void listGenres() {
        System.out.println("Список жанров");
        genreRepository.findAll().forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "View all books", key = {"b", "books"})
    public void listAllBooks() {
        System.out.println("Список всех книг");
        bookRepository.findAll().forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "View book details", key = {"bd", "book-details"})
    public void bookDetails(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Not found"));
        System.out.println("Ид: " + book.getId());
        System.out.println("Название: " + book.getName());
        System.out.println("Автор: " + book.getAuthor());
        System.out.println("Жанр: " + book.getGenre());
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "View books by author", key = {"ba", "books-by-author"})
    public void listBooksByAuthor(Long authorId) {
        bookRepository.findByAuthorId(authorId).forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "View books by genre", key = {"bg", "books-by-genre"})
    public void listBookByGenre(Long genreId) {
        bookRepository.findByGenreId(genreId).forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "View comments by book", key = {"bc", "comments-by-book"})
    public void listCommentsByBook(Long bookId) {
        bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("not found"))
                .getComments()
                .forEach(System.out::println);
    }
}
