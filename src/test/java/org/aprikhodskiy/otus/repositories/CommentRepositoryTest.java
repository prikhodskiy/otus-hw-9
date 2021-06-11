package org.aprikhodskiy.otus.repositories;

import org.aprikhodskiy.otus.models.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Репозиторий на основе Jpa для работы с комментариями")
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository repositoryJpa;

    @Autowired
    TestEntityManager em;

    @DisplayName("должен загружать список всех комментариев ")
    @Test
    void shouldReturnCorrectCommentsList() {
        var comments = repositoryJpa.findAll();
        assertNotNull(comments);
        assertThat(comments.size()).isGreaterThan(4);
    }

    @DisplayName("должен получать комментарий по id  ")
    @Test
    void shouldReturnCorrectCommentById() {
        var comment = repositoryJpa.findById(3L).orElse(null);
        assertNotNull(comment);
        assertEquals("comment 3", comment.getText());
    }


    @DisplayName("должен уметь добавлять комментарий ")
    @Test
    void shouldAddNewComment() {
        long bookId = 4L;
        int initialCount = repositoryJpa.findAll().size();

        Comment newComment = Comment.builder()
                .text("Some text")
                .bookId(bookId)
                .build();

        repositoryJpa.save(newComment);

        assertThat(initialCount + 1).isEqualTo(repositoryJpa.findAll().size());
    }

    @DisplayName("должен уметь обновлять комментарий ")
    @Test
    void shouldUpdateBook() {
        Comment commentBeforeUpdate = repositoryJpa.findById(77L).orElse(null);
        assertEquals("This will be updated by test", commentBeforeUpdate.getText());
        commentBeforeUpdate.setText("textAfterUpdate");
        repositoryJpa.save(commentBeforeUpdate);
        em.flush();
        Comment commentAfterUpdate = repositoryJpa.findById(77L).orElse(null);
        assertEquals("textAfterUpdate", commentAfterUpdate.getText());
    }

    @DisplayName("должен уметь удалять комментарий")
    @Test
    void shouldDeleteById() {
        assertThatCode(() -> repositoryJpa.findById(13L))
                .doesNotThrowAnyException();
        int initialCount = repositoryJpa.findAll().size();

        repositoryJpa.deleteById(13L);
        em.flush();

        assertThat(initialCount - 1).isEqualTo(repositoryJpa.findAll().size());
    }
}