package org.aprikhodskiy.otus.shell;

import lombok.RequiredArgsConstructor;
import org.aprikhodskiy.otus.dao.AuthorDao;
import org.aprikhodskiy.otus.dao.BookDao;
import org.aprikhodskiy.otus.dao.GenreDao;
import org.aprikhodskiy.otus.domain.Book;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {
    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final GenreDao genreDao;


    @ShellMethod(value = "View authors", key = {"a", "authors"})
    public void listAuthors() {
        System.out.println("Список авторов");
        authorDao.getAll().forEach(System.out::println);
    }

    @ShellMethod(value = "View genres", key = {"g", "genres"})
    public void listGenres() {
        System.out.println("Список жанров");
        genreDao.getAll().forEach(System.out::println);
    }

    @ShellMethod(value = "View all books", key = {"b", "books"})
    public void listAllBooks() {
        System.out.println("Список всех книг");
        genreDao.getAll().forEach(System.out::println);
    }

    @ShellMethod(value = "View book details", key = {"bd", "book-details"})
    public void bookDetails(Long bookId) {
        Book book = bookDao.getById(bookId);
        System.out.println("Ид: " + book.getId());
        System.out.println("Название: " + book.getName());
        System.out.println("Автор: " + book.getAuthor());
        System.out.println("Жанр: " + book.getGenre());
    }

    @ShellMethod(value = "View books by author", key = {"ba", "books-by-author"})
    public void listBooksByAuthor(Long authorId) {
        bookDao.getByAuthorId(authorId).forEach(System.out::println);
    }

    @ShellMethod(value = "View books by genre", key = {"bg", "books-by-genre"})
    public void listBookByGenre(Long genreId) {
        bookDao.getByGenreId(genreId).forEach(System.out::println);
    }
}
