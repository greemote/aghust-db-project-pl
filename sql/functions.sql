SET search_path to BD_projekt;

CREATE OR REPLACE FUNCTION checkClientEmail(email1 TEXT) 
RETURNS INTEGER AS
$$
    DECLARE
        b INTEGER := 0;
    BEGIN
        SELECT INTO b 1 WHERE EXISTS(SELECT 1 FROM BD_projekt.klient k WHERE k.email = email1);
        IF (b = 1) THEN
            RETURN 0;
        ELSE
            RETURN 1;
        END IF;
    END
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION checkClientLogin(login1 TEXT) 
RETURNS INTEGER AS
$$
    DECLARE
        b INTEGER := 0;
    BEGIN
        SELECT INTO b 1 WHERE EXISTS(SELECT 1 FROM BD_projekt.logowanie_klient lk WHERE lk.login = login1);
        IF (b = 1) THEN
            RETURN 0;
        ELSE
            RETURN 1;
        END IF;
    END
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION getLastClientId()
RETURNS INTEGER AS
$$
    DECLARE id INTEGER;
    BEGIN
        SELECT INTO id MAX(id_klient) FROM BD_projekt.klient;
        IF(id IS NULL) THEN
            RETURN 0;
        ELSE
            RETURN id;
        END IF;
    END
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION getLastDirectorId()
RETURNS INTEGER AS
$$
    DECLARE id INTEGER;
    BEGIN
        SELECT INTO id MAX(id_rezyser) FROM BD_projekt.rezyser;
        IF(id IS NULL) THEN
            RETURN 0;
        ELSE
            RETURN id;
        END IF;
    END
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION getLastActorId()
RETURNS INTEGER AS
$$
    DECLARE id INTEGER;
    BEGIN
        SELECT INTO id MAX(id_aktor) FROM BD_projekt.aktor;
        IF(id IS NULL) THEN
            RETURN 0;
        ELSE
            RETURN id;
        END IF;
    END
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION getLastMovieId()
RETURNS INTEGER AS
$$
    DECLARE id INTEGER;
    BEGIN
        SELECT INTO id MAX(id_film) FROM BD_projekt.film;
        IF(id IS NULL) THEN
            RETURN 0;
        ELSE
            RETURN id;
        END IF;
    END
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION getLastShowId()
RETURNS INTEGER AS
$$
    DECLARE id INTEGER;
    BEGIN
        SELECT INTO id MAX(id_seans) FROM BD_projekt.seans;
        IF(id IS NULL) THEN
            RETURN 0;
        ELSE
            RETURN id;
        END IF;
    END
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION getLastPaymentId()
RETURNS INTEGER AS
$$
    DECLARE id INTEGER;
    BEGIN
        SELECT INTO id MAX(id_platnosc) FROM BD_projekt.platnosc;
        IF(id IS NULL) THEN
            RETURN 0;
        ELSE
            RETURN id;
        END IF;
    END
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION getLastWorkerId()
RETURNS INTEGER AS
$$
    DECLARE id INTEGER;
    BEGIN
        SELECT INTO id MAX(id_pracownik) FROM BD_projekt.pracownik;
        IF(id IS NULL) THEN
            RETURN 0;
        ELSE
            RETURN id;
        END IF;
    END
$$
LANGUAGE 'plpgsql';