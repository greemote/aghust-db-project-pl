SET search_path to BD_projekt;

INSERT INTO administrator (id_administrator, imie, nazwisko, email, telefon)
VALUES
(1, 'Krzysztof', 'Jarzyna', 'kjarzyna@kino.pl', '123456789');

INSERT INTO logowanie_admin (id_administrator, login, haslo)
VALUES
(1, 'admin', 'admin');

INSERT INTO forma_platnosci (id_forma, nazwa)
VALUES
(1, 'gotowka'),
(2, 'karta'),
(3, 'przelew');

INSERT INTO tlumaczenie(id_tlumaczenie, nazwa)
VALUES
(1, 'napisy'),
(2, 'dubbing');

INSERT INTO technologia(id_technologia, nazwa)
VALUES
(1, '2D'),
(2, '3D');

INSERT INTO kino(id_kino, miejscowosc)
VALUES
(1, 'Kraków'),
(2, 'Warszawa'),
(3, 'Poznań');

INSERT INTO sala(id_sala, id_kino, ilosc_miejsc)
VALUES
(1, 1, 100),
(2, 1, 200),
(3, 2, 100),
(4, 2, 200),
(5, 2, 300),
(6, 3, 100),
(7, 3, 200);

INSERT INTO gatunek(id_gatunek, nazwa)
VALUES
(1, 'akcja'),
(2, 'animacja'),
(3, 'dramat'),
(4, 'komedia'),
(5, 'horror'),
(6, 'fantastyka'),
(7, 'historyczny');

INSERT INTO kategoria_wiekowa(id_kategoria, nazwa)
VALUES
(1, '+7'),
(2, '+12'),
(3, '+16');