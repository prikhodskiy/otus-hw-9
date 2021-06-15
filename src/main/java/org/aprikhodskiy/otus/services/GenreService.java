package org.aprikhodskiy.otus.services;

import lombok.AllArgsConstructor;
import org.aprikhodskiy.otus.dto.GenreDto;
import org.aprikhodskiy.otus.dto.ModelMapper;
import org.aprikhodskiy.otus.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public List<GenreDto> findAllGenres() {
        return genreRepository.findAll()
                .stream().map(ModelMapper::toDto)
                .collect(Collectors.toList());
    }

}
