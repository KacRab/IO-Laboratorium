-- Struktura tabel
CREATE TABLE `ksiegarnia`
(
    `id`   int          NOT NULL AUTO_INCREMENT,
    `logo` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `autor`
(
    `id`        int          NOT NULL AUTO_INCREMENT,
    `firstname` varchar(255) NOT NULL,
    `lastname`  varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `ksiazka`
(
    `id`        int          NOT NULL AUTO_INCREMENT,
    `cover`    varchar(255) NOT NULL,
    `rating`    float DEFAULT NULL,
    `title`     varchar(255) NOT NULL,
    `autor_id`  int   DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `ksiazka_ksiegarnia`
(
    `ksiazka_id`    int DEFAULT NULL,
    `ksiegarnia_id` int DEFAULT NULL
);

-- Dane dla tabeli ksiegarnia
INSERT INTO `ksiegarnia` (`id`, `name`, `logo`)
VALUES (1, 'Empik', 'https://upload.wikimedia.org/wikipedia/commons/7/70/Empik_logo_2013.png'),
       (2, 'Swiat Ksiazki', 'https://upload.wikimedia.org/wikipedia/commons/4/4f/Swiat_Ksiazki_logo.png'),
       (3, 'TaniaKsiazka.pl', 'https://www.taniaksiazka.pl/images/logo.png'),
       (4, 'Ksiegarnia Znak', 'https://www.znak.com.pl/images/logo.png');

-- Dane dla tabeli autor
INSERT INTO `autor` (`id`, `firstname`, `lastname`)
VALUES (1, 'Andrzej', 'Sapkowski'),
       (2, 'Olga', 'Tokarczuk'),
       (3, 'Stanislaw', 'Lem'),
       (4, 'Adam', 'Mickiewicz');

-- Dane dla tabeli ksiazka
INSERT INTO `ksiazka` (`id`, `title`, `cover`, `autor_id`, `rating`)
VALUES (1, 'Wiedzmin: Ostatnie zyczenie', 'https://ecsmedia.pl/c/ostatnie-zyczenie-wiedzmin-tom-1-b-iext71634375.jpg', 1, 4.8),
       (2, 'Krew elfow', 'https://ecsmedia.pl/c/krew-elfow-wiedzmin-tom-3-b-iext71634377.jpg', 1, 4.7),
       (3, 'Bieguni', 'https://ecsmedia.pl/c/bieguni-b-iext71252729.jpg', 2, 4.3),
       (4, 'Ksiegi Jakubowe', 'https://ecsmedia.pl/c/ksiegi-jakubowe-b-iext71354589.jpg', 2, 4.6),
       (5, 'Solaris', 'https://ecsmedia.pl/c/solaris-b-iext71624861.jpg', 3, 4.9),
       (6, 'Cyberiada', 'https://ecsmedia.pl/c/cyberiada-b-iext71624858.jpg', 3, 4.5),
       (7, 'Dziady', 'https://ecsmedia.pl/c/dziady-b-iext71625355.jpg', 4, 4.4),
       (8, 'Pan Tadeusz', 'https://ecsmedia.pl/c/pan-tadeusz-b-iext71625357.jpg', 4, 4.6);

-- Relacje ksiazka <-> ksiegarnia (na podstawie metod bind w SampleData)
INSERT INTO `ksiazka_ksiegarnia` (`ksiegarnia_id`, `ksiazka_id`)
VALUES (1, 1), -- Empik, Wiedzmin
       (3, 1), -- TaniaKsiazka, Wiedzmin
       (3, 3), -- TaniaKsiazka, Bieguni
       (3, 5), -- TaniaKsiazka, Solaris
       (1, 6), -- Empik, Cyberiada
       (4, 6), -- Znak, Cyberiada
       (4, 7), -- Znak, Dziady
       (2, 7), -- Swiat Ksiazki, Dziady
       (2, 3); -- Swiat Ksiazki, Bieguni

-- Tabele użytkowników i ról (bez zmian w strukturze)
CREATE TABLE user
(
    id       int primary key auto_increment,
    username VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE role
(
    id       int primary key auto_increment,
    username VARCHAR(255),
    role     VARCHAR(255)
);

INSERT INTO user(username, password)
VALUES ('dbuser1', 'dbuser1'),
       ('dbuser2', 'dbuser2'),
       ('dbuser3', 'dbuser3');

INSERT INTO role(username, role)
VALUES ('dbuser1', 'ROLE_ADMIN'),
       ('dbuser2', 'ROLE_AUTHOR_ADMIN'),
       ('dbuser3', 'ROLE_BOOK_ADMIN');