package org.aprikhodskiy.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aprikhodskiy.otus.models.Genre;

@Getter
@AllArgsConstructor
public class GenreDto {
    private long id;
    private String name;

    public static GenreDto toDto(Genre genre){
        return new GenreDto(genre.getId(), genre.getName());
    }
}
