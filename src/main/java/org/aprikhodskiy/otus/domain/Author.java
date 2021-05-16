package org.aprikhodskiy.otus.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Author {
    private final long id;
    private final String name;

    @Override
    public String toString() {
        return String.format("%s (id = %d)", name, id);
    }
}
