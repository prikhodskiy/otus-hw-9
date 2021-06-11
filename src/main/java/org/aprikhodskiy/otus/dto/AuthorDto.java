package org.aprikhodskiy.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aprikhodskiy.otus.models.Author;

@Getter
@AllArgsConstructor
public class AuthorDto {
    private long id;
    private String name;

    public static AuthorDto toDto(Author author){
        return new AuthorDto(author.getId(), author.getName());
    }
}
