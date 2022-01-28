CREATE VIEW wszystkieFilmy AS
SELECT tytul, r.imie, r.nazwisko, g.nazwa AS gatunek, k.nazwa AS kategoria
FROM BD_projekt.film f, BD_projekt.rezyser r, BD_projekt.gatunek g, BD_projekt.kategoria_wiekowa k
WHERE
f.id_rezyser = r.id_rezyser AND f.id_gatunek = g.id_gatunek AND f.id_kategoria = k.id_kategoria;