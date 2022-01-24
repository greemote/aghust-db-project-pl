CREATE SCHEMA BD_projekt;
SET search_path to BD_projekt;

CREATE TABLE film
(
  id_film Int NOT NULL,
  id_rezyser Int NOT NULL,
  id_gatunek Int NOT NULL,
  id_kategoria Int NOT NULL,
  tytul Varchar(50) NOT NULL
);
ALTER TABLE film ADD PRIMARY KEY (id_film);

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
  id_sala Int NOT NULL,
  id_technologia Int NOT NULL,
  id_tlumaczenie Int,
  data Varchar(10) NOT NULL,
  godzina_rozpoczecia Varchar(5) NOT NULL
);
ALTER TABLE seans ADD PRIMARY KEY (id_seans);

CREATE TABLE pracownik
(
  id_pracownik Int NOT NULL,
  id_kino Int NOT NULL,
  imie Varchar(50) NOT NULL,
  nazwisko Varchar(50) NOT NULL,
  email Varchar(50) NOT NULL,
  telefon Varchar(50) NOT NULL
);
ALTER TABLE pracownik ADD PRIMARY KEY (id_pracownik);

CREATE TABLE bilet
(
  id_bilet Int NOT NULL,
  id_platnosc Int NOT NULL,
  id_seans Int NOT NULL,
  rzad Varchar(1) NOT NULL,
  numer_miejsca Int NOT NULL
);
ALTER TABLE bilet ADD PRIMARY KEY (id_bilet, id_seans, id_platnosc);

CREATE TABLE sala
(
  id_sala Int NOT NULL,
  id_kino Int NOT NULL,
  ilosc_miejsc Int NOT NULL
);
ALTER TABLE sala ADD PRIMARY KEY (id_sala);

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
  login Varchar(20) NOT NULL,
  haslo Varchar(20) NOT NULL
);
ALTER TABLE logowanie_pracownik ADD PRIMARY KEY (id_pracownik);

CREATE TABLE platnosc
(
  id_platnosc Int NOT NULL,
  id_klient Int NOT NULL,
  id_forma Int NOT NULL,
  wartosc Decimal(5,2) NOT NULL
);
ALTER TABLE platnosc ADD PRIMARY KEY (id_platnosc);

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
  id_aktor Int NOT NULL
);
ALTER TABLE aktor_film ADD PRIMARY KEY (id_film, id_aktor);

CREATE TABLE forma_platnosci
(
  id_forma Int NOT NULL,
  nazwa Varchar(20) NOT NULL
);
ALTER TABLE forma_platnosci ADD PRIMARY KEY (id_forma);

CREATE TABLE technologia
(
  id_technologia Int NOT NULL,
  nazwa Varchar(2) NOT NULL
);
ALTER TABLE technologia ADD PRIMARY KEY (id_technologia);

CREATE TABLE tlumaczenie
(
  id_tlumaczenie Int NOT NULL,
  nazwa Varchar(20) NOT NULL
);
ALTER TABLE tlumaczenie ADD PRIMARY KEY (id_tlumaczenie);

CREATE TABLE gatunek
(
  id_gatunek Int NOT NULL,
  nazwa Varchar(20) NOT NULL
);
ALTER TABLE gatunek ADD PRIMARY KEY (id_gatunek);

CREATE TABLE kategoria_wiekowa
(
  id_kategoria Int NOT NULL,
  nazwa Varchar(3) NOT NULL
);
ALTER TABLE kategoria_wiekowa ADD PRIMARY KEY (id_kategoria);

ALTER TABLE sala ADD FOREIGN KEY (id_kino) REFERENCES kino (id_kino);
ALTER TABLE seans ADD FOREIGN KEY (id_film) REFERENCES film (id_film);
ALTER TABLE pracownik ADD FOREIGN KEY (id_kino) REFERENCES kino (id_kino);
ALTER TABLE seans ADD FOREIGN KEY (id_sala) REFERENCES sala (id_sala);
ALTER TABLE bilet ADD FOREIGN KEY (id_seans) REFERENCES seans (id_seans);
ALTER TABLE logowanie_klient ADD FOREIGN KEY (id_klient) REFERENCES klient (id_klient);
ALTER TABLE logowanie_admin ADD FOREIGN KEY (id_administrator) REFERENCES administrator (id_administrator);
ALTER TABLE logowanie_pracownik ADD FOREIGN KEY (id_pracownik) REFERENCES pracownik (id_pracownik);
ALTER TABLE bilet ADD FOREIGN KEY (id_platnosc) REFERENCES platnosc (id_platnosc);
ALTER TABLE platnosc ADD FOREIGN KEY (id_klient) REFERENCES klient (id_klient);
ALTER TABLE film ADD FOREIGN KEY (id_rezyser) REFERENCES rezyser (id_rezyser);
ALTER TABLE aktor_film ADD FOREIGN KEY (id_film) REFERENCES film (id_film);
ALTER TABLE aktor_film ADD FOREIGN KEY (id_aktor) REFERENCES aktor (id_aktor);
ALTER TABLE platnosc ADD FOREIGN KEY (id_forma) REFERENCES forma_platnosci (id_forma);
ALTER TABLE seans ADD FOREIGN KEY (id_technologia) REFERENCES technologia (id_technologia);
ALTER TABLE seans ADD FOREIGN KEY (id_tlumaczenie) REFERENCES tlumaczenie (id_tlumaczenie);
ALTER TABLE film ADD FOREIGN KEY (id_gatunek) REFERENCES gatunek (id_gatunek);
ALTER TABLE film ADD FOREIGN KEY (id_kategoria) REFERENCES kategoria_wiekowa (id_kategoria);

