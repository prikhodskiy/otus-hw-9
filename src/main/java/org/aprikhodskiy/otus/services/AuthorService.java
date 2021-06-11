package org.aprikhodskiy.otus.services;

import lombok.AllArgsConstructor;
import org.aprikhodskiy.otus.dto.AuthorDto;
import org.aprikhodskiy.otus.dto.ModelMapper;
import org.aprikhodskiy.otus.repositories.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AuthorService {
    private final AuthorRepository authorRepository;


    @Transactional(readOnly = true)
    public List<AuthorDto> findAllAuthors() {
        return authorRepository.findAll()
                .stream().map(ModelMapper::toDto)
                .collect(Collectors.toList());
    }
}
