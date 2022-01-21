SET search_path TO BD_projekt;

CREATE TABLE film
(
  id_film Int NOT NULL,
  id_rezyser Int NOT NULL,
  tytul Varchar(50) NOT NULL,
  gatunek Varchar(50) NOT NULL,
  kategoria_wiekowa Varchar(2) NOT NULL
);
ALTER TABLE film ADD PRIMARY KEY (id_film, id_rezyser);

CREATE TABLE kino
(
  id_kino Int NOT NULL,
  miejscowosc Varchar(50) NOT NULL
);
ALTER TABLE kino ADD PRIMARY KEY (id_kino);

CREATE TABLE seans
(
  id_seans Int NOT NULL,
  id_film Int NOT NULL,
  id_rezyser Int NOT NULL,
  id_sala Int NOT NULL,
  id_kino Int NOT NULL,
  data Varchar(10) NOT NULL,
  godzina_rozpoczecia Varchar(5) NOT NULL,
  technologia Varchar(2) NOT NULL,
  tlumaczenie Varchar(20) NOT NULL
);
ALTER TABLE seans ADD PRIMARY KEY (id_seans, id_film, id_sala, id_kino, id_rezyser);

CREATE TABLE pracownik
(
  id_pracownik Int NOT NULL,
  id_kino Int NOT NULL,
  imie Varchar(50) NOT NULL,
  nazwisko Varchar(50) NOT NULL,
  email Varchar(50) NOT NULL,
  telefon Varchar(50) NOT NULL
);
ALTER TABLE pracownik ADD PRIMARY KEY (id_pracownik, id_kino);

CREATE TABLE bilet
(
  id_bilet Int NOT NULL,
  id_platnosc Int NOT NULL,
  id_klient Int NOT NULL,
  id_seans Int NOT NULL,
  id_sala Int NOT NULL,
  id_kino Int NOT NULL,
  id_film Int NOT NULL,
  id_rezyser Int NOT NULL,
  rzad Varchar(1) NOT NULL,
  numer_miejsca Int NOT NULL
);
ALTER TABLE bilet ADD PRIMARY KEY (id_bilet, id_seans, id_film, id_sala, id_kino, id_platnosc, id_klient, id_rezyser);

CREATE TABLE sala
(
  id_sala Int NOT NULL,
  id_kino Int NOT NULL,
  ilosc_miejsc Int NOT NULL
);
ALTER TABLE sala ADD PRIMARY KEY (id_sala, id_kino);

CREATE TABLE administrator
(
  id_administrator Int NOT NULL,
  imie Varchar(50) NOT NULL,
  nazwisko Varchar(50) NOT NULL,
  email Varchar(50) NOT NULL,
  telefon Varchar(50) NOT NULL
);
ALTER TABLE administrator ADD PRIMARY KEY (id_administrator);

CREATE TABLE klient
(
  id_klient Int NOT NULL,
  imie Varchar(50) NOT NULL,
  nazwisko Varchar(50) NOT NULL,
  email Varchar(50) NOT NULL,
  telefon Varchar(20)
);
ALTER TABLE klient ADD PRIMARY KEY (id_klient);

CREATE TABLE logowanie_klient
(
  id_klient Int NOT NULL,
  login Varchar(20) NOT NULL,
  haslo Varchar(20) NOT NULL
);
ALTER TABLE logowanie_klient ADD PRIMARY KEY (id_klient);

CREATE TABLE logowanie_admin
(
  id_administrator Int NOT NULL,
  login Varchar(20) NOT NULL,
  haslo Varchar(20) NOT NULL
);
ALTER TABLE logowanie_admin ADD PRIMARY KEY (id_administrator);

CREATE TABLE logowanie_pracownik
(
  id_pracownik Int NOT NULL,
  id_kino Int NOT NULL,
  login Varchar(20) NOT NULL,
  haslo Varchar(20) NOT NULL
);
ALTER TABLE logowanie_pracownik ADD PRIMARY KEY (id_pracownik, id_kino);

CREATE TABLE platnosc
(
  id_platnosc Int NOT NULL,
  id_klient Int NOT NULL,
  forma Varchar(20) NOT NULL,
  wartosc Decimal(5,2) NOT NULL
);
ALTER TABLE platnosc ADD PRIMARY KEY (id_platnosc, id_klient);

CREATE TABLE rezyser
(
  id_rezyser Int NOT NULL,
  imie Varchar(50) NOT NULL,
  nazwisko Varchar(50) NOT NULL
);
ALTER TABLE rezyser ADD PRIMARY KEY (id_rezyser);

CREATE TABLE aktor
(
  id_aktor Int NOT NULL,
  imie Varchar(50) NOT NULL,
  nazwisko Varchar(50) NOT NULL
);
ALTER TABLE aktor ADD PRIMARY KEY (id_aktor);

CREATE TABLE aktor_film
(
  id_film Int NOT NULL,
  id_rezyser Int NOT NULL,
  id_aktor Int NOT NULL
);
ALTER TABLE aktor_film ADD PRIMARY KEY (id_film, id_rezyser, id_aktor);

ALTER TABLE sala ADD FOREIGN KEY (id_kino) REFERENCES kino (id_kino);
ALTER TABLE seans ADD FOREIGN KEY (id_film, id_rezyser) REFERENCES film (id_film, id_rezyser);
ALTER TABLE pracownik ADD FOREIGN KEY (id_kino) REFERENCES kino (id_kino);
ALTER TABLE seans ADD FOREIGN KEY (id_sala, id_kino) REFERENCES sala (id_sala, id_kino);
ALTER TABLE bilet ADD FOREIGN KEY (id_seans, id_film, id_sala, id_kino, id_rezyser) REFERENCES seans (id_seans, id_film, id_sala, id_kino, id_rezyser);
ALTER TABLE logowanie_klient ADD FOREIGN KEY (id_klient) REFERENCES klient (id_klient);
ALTER TABLE logowanie_admin ADD FOREIGN KEY (id_administrator) REFERENCES administrator (id_administrator);
ALTER TABLE logowanie_pracownik ADD FOREIGN KEY (id_pracownik, id_kino) REFERENCES pracownik (id_pracownik, id_kino);
ALTER TABLE bilet ADD FOREIGN KEY (id_platnosc, id_klient) REFERENCES platnosc (id_platnosc, id_klient);
ALTER TABLE platnosc ADD FOREIGN KEY (id_klient) REFERENCES klient (id_klient);
ALTER TABLE film ADD FOREIGN KEY (id_rezyser) REFERENCES rezyser (id_rezyser);
ALTER TABLE aktor_film ADD FOREIGN KEY (id_film, id_rezyser) REFERENCES film (id_film, id_rezyser);
ALTER TABLE aktor_film ADD FOREIGN KEY (id_aktor) REFERENCES aktor (id_aktor);

