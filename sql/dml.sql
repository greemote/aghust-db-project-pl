SET search_path to BD_projekt;

INSERT INTO administrator (id_administrator, imie, nazwisko, email, telefon)
VALUES
(1, 'Krzysztof', 'Jarzyna', 'kjarzyna@kino.pl', '123456789');

INSERT INTO logowanie_admin (id_administrator, login, haslo)
VALUES
(1, 'admin', 'admin');

INSERT INTO forma_platnosci (id_forma, nazwa)
VALUES
(1, 'gotówka'),
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

insert into bd_projekt.pracownik values
(1, 1, 'Michał', 'Nowak', 'mnowak@kino.pl', '223475123'),
(2, 1, 'Amelia', 'Kowalska', 'akowalska@kino.pl', '502769240'),
(3, 1, 'Ryszard', 'Kwiatkowski', 'kwiatek@kino.pl', '590100943'),
(4, 1, 'Zdzisław', 'Kaczor', 'kaczka@kino.pl', '479643272'),
(5, 2, 'Małgorzata', 'Leśniak', 'mlesniak@kino.pl', '839405212'),
(6, 2, 'Krzysztof', 'Kamiński', 'kam@kino.pl', '782957568'),
(7, 2, 'Emil', 'Pączek', 'emilek@kino.pl', '837535040'),
(8, 2, 'Artur', 'Nowak', 'arczi@kino.pl', '209487666'),
(9, 2, 'Sylwester', 'Pędziwiatr', 'strus@kino.pl', '333456789'),
(10, 2, 'Honorata', 'Szyszka', 'honka@kino.pl', '112345098'),
(11, 3, 'Mirosław', 'Wiewiór', 'wiewior@kino.pl', '567345098'),
(12, 3, 'Sylwia', 'Ryba', 'sryba@kino.pl', '357892345'),
(13, 3, 'Anna', 'Duda', 'duda@kino.pl', '111234672'),
(14, 3, 'Stanisław', 'Wyspiański', 'wyspianski@kino.pl', '563923546');

insert into bd_projekt.logowanie_pracownik values 
(1, 'michal', 'nowak'),
(2, 'amelia', 'kowalska'),
(3, 'ryszard', 'kwiatkowski'),
(4, 'zdzislaw', 'kaczor'),
(5, 'malgorzata', 'lesniak'),
(6, 'krzysztof', 'kaminski'),
(7, 'emil', 'paczek'),
(8, 'artur', 'nowak'),
(9, 'sylwester', 'pedziwiatr'),
(10, 'honorata', 'szyszka'),
(11, 'miroslaw', 'wiewior'),
(12, 'sylwia', 'ryba'),
(13, 'anna', 'duda'),
(14, 'stanislaw', 'wyspianski');
