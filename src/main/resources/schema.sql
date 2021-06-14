create table authors(
    id bigint primary key,
    name varchar(255),
    unique uq_author_1 (name)
);

create table genres(
    id bigint primary key auto_increment,
    name varchar(255),
    unique uq_genres_1 (name)
);

create table books(
    id bigint primary key auto_increment,
    name varchar(255),
    author_id bigint,
    genre_id bigint,
    unique uq_author_book_1 (author_id, name),
    constraint fk_books_authors foreign key (author_id) references authors (id),
    constraint fk_books_genres foreign key (genre_id) references genres (id)
);

create table comments(
    id bigint primary key auto_increment,
    text varchar(1028),
    book_id bigint,
    constraint fk_comments_books foreign key (book_id) references books (id)
);

create table users(
    id bigint primary key auto_increment,
    name varchar(255),
    username varchar(80),
    password varchar(255),
    unique uq_users_1 (name)
);