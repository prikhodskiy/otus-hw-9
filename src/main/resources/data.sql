insert into genres (id, `name`) values (1, 'Short stories');
insert into genres (id, `name`) values (2, 'Sci-Fi');
insert into genres (id, `name`) values (3, 'Novel');

insert into authors (id, `name`) values (1, 'Leo Tolstoy');
insert into authors (id, `name`) values (2, 'Fyodor Dostoevsky');

insert into books (id, `name`, author_id, genre_id) values (1, 'War and Peace', 1, 3);
insert into books (id, `name`, author_id, genre_id) values (2, 'Anna Karenina', 1, 3);
insert into books (id, `name`, author_id, genre_id) values (3, 'The Brothers Karamazov', 2, 3);
insert into books (id, `name`, author_id, genre_id) values (4, 'An Honest Thief', 2, 1);


insert into comments (id, `text`, book_id) values (1, 'comment 1', 3);
insert into comments (id, `text`, book_id) values (2, 'comment 2', 2);
insert into comments (id, `text`, book_id) values (3, 'comment 3', 2);
insert into comments (id, `text`, book_id) values (4, 'comment 4', 2);
insert into comments (id, `text`, book_id) values (5, 'comment 5', 2);
