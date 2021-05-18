package org.aprikhodskiy.otus.dao;

import org.aprikhodskiy.otus.domain.Author;
import org.aprikhodskiy.otus.domain.Book;
import org.aprikhodskiy.otus.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    private static final String SQL_SELECT_BASE = "select b.id, b.name, a.id as authorId, a.name as authorName, g.id as genreId, g.name as genreName " +
            "from books b " +
            "join authors a on a.id = b.author_id " +
            "join genres g on g.id = b.genre_id ";

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query(SQL_SELECT_BASE, new BookMapper());
    }

    @Override
    public void insert(Book book) {
        namedParameterJdbcOperations.update("insert into books (id, `name`, author_id, genre_id) values (:id, :name, :authorId, :genreId)",
                Map.of("id", book.getId(),
                        "name", book.getName(),
                        "authorId", book.getAuthor().getId(),
                        "genreId",book.getGenre().getId())
        );
    }

    @Override
    public void update(Book book) {
        namedParameterJdbcOperations.update("update books set `name`= :name where id = :id",
                Map.of("id", book.getId(), "name", book.getName()));
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject
                (SQL_SELECT_BASE + "where b.id = :id", params, new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );

    }

    @Override
    public List<Book> getByAuthorId(Long authorId) {
        Map<String, Object> params = Collections.singletonMap("author_id", authorId);
        return namedParameterJdbcOperations.query
                (SQL_SELECT_BASE + "where a.id = :author_id", params, new BookMapper());
    }

    @Override
    public List<Book> getByGenreId(Long genreId) {
        Map<String, Object> params = Collections.singletonMap("genre_id", genreId);
        return namedParameterJdbcOperations.query
                (SQL_SELECT_BASE + "where g.id = :genre_id", params, new BookMapper());
    }

    private class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");

            long authorId = resultSet.getLong("authorId");
            String authorName = resultSet.getString("authorName");

            long genreId = resultSet.getLong("genreId");
            String genreName = resultSet.getString("genreName");

            return new Book(id, name, new Author(authorId, authorName), new Genre(genreId, genreName));
        }
    }
}
