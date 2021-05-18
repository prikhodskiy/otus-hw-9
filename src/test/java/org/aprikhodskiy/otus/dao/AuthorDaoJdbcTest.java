package org.aprikhodskiy.otus.dao;

import org.aprikhodskiy.otus.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedGenreList() {
        List<Author> authors = authorDaoJdbc.getAll();
        assertThat(authors).isNotNull();
    }

    @DisplayName("содержать Достоевского")
    @Test
    void shouldReturnSciFi() {
        List<Author> authors = authorDaoJdbc.getAll();
        assertThat(authors).filteredOn(author -> author.getName().equals("Fyodor Dostoevsky")).hasSize(1);
    }
}