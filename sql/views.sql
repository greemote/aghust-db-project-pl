CREATE VIEW wszystkieFilmy AS
SELECT tytul, r.imie, r.nazwisko, g.nazwa AS gatunek, k.nazwa AS kategoria
FROM BD_projekt.film f, BD_projekt.rezyser r, BD_projekt.gatunek g, BD_projekt.kategoria_wiekowa k
WHERE
f.id_rezyser = r.id_rezyser AND f.id_gatunek = g.id_gatunek AND f.id_kategoria = k.id_kategoria;

CREATE VIEW wszystkieSeanse AS
SELECT id_seans, tytul, miejscowosc, sn.id_sala, sl.ilosc_miejsc AS sala, tch.nazwa AS technologia, tl.nazwa AS tlumaczenie, data, godzina_rozpoczecia
FROM BD_projekt.film f, BD_projekt.kino k, BD_projekt.sala sl, BD_projekt.technologia tch, BD_projekt.seans sn
LEFT JOIN bd_projekt.tlumaczenie tl ON sn.id_tlumaczenie = tl.id_tlumaczenie
WHERE sn.id_sala = sl.id_sala AND sl.id_kino = k.id_kino AND sn.id_technologia = tch.id_technologia AND sn.id_film = f.id_film;

CREATE VIEW mojeBilety AS
SELECT id_klient, id_bilet, wartosc, tytul, miejscowosc, s.id_sala, b.rzad, b.numer_miejsca, data, godzina_rozpoczecia
FROM BD_projekt.platnosc p, BD_projekt.film f, BD_projekt.sala sl, BD_Projekt.kino k, BD_projekt.bilet b, BD_projekt.seans s
WHERE p.id_platnosc = b.id_platnosc AND f.id_film = s.id_film AND k.id_kino = sl.id_kino AND s.id_seans = b.id_seans AND s.id_sala = sl.id_sala;

CREATE VIEW pracownicy AS
SELECT id_pracownik, miejscowosc, imie, nazwisko, email, telefon FROM bd_projekt.pracownik p, bd_projekt.kino k
WHERE p.id_kino = k.id_kino;