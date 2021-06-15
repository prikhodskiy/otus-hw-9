package org.aprikhodskiy.otus.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aprikhodskiy.otus.dto.AuthorDto;
import org.aprikhodskiy.otus.dto.BookDetailDto;
import org.aprikhodskiy.otus.dto.BookDto;
import org.aprikhodskiy.otus.dto.GenreDto;
import org.aprikhodskiy.otus.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookService bookService;

    @Test
    void shouldReturnCorrectBooksList() throws Exception {
        List<BookDto> books = List.of(
                new BookDto(1, "book1", "author name", "genre name"),
                new BookDto(2, "book2", "author name", "genre name")
        );
        given(bookService.findAllBooks()).willReturn(books);

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(books)));
    }

    @Test
    void shouldReturnCorrectBookById() throws Exception {
        BookDetailDto book = new BookDetailDto(2, "book2", new AuthorDto(5,"author name"), new GenreDto(3,"genre name"), null);

        given(bookService.fineOneBook(2L)).willReturn(book);

        mvc.perform(get("/books/2"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(book)));
    }

}