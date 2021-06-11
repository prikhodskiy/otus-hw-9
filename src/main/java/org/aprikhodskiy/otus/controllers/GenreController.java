package org.aprikhodskiy.otus.controllers;

import lombok.AllArgsConstructor;
import org.aprikhodskiy.otus.dto.GenreDto;
import org.aprikhodskiy.otus.services.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class GenreController {

    private final GenreService genreService;

    @RequestMapping("/genres")
    public ResponseEntity<List<GenreDto>> genres() {
        return ResponseEntity.status(HttpStatus.OK).body(
                genreService.findAllGenres()
        );
    }
}
