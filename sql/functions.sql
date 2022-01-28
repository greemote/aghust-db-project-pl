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
        SELECT INTO id id_klient FROM BD_projekt.klient ORDER BY id_klient DESC LIMIT 1;
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
        SELECT INTO id id_rezyser FROM BD_projekt.rezyser ORDER BY id_rezyser DESC LIMIT 1;
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
        SELECT INTO id id_aktor FROM BD_projekt.aktor ORDER BY id_aktor DESC LIMIT 1;
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
        SELECT INTO id id_film FROM BD_projekt.film ORDER BY id_film DESC LIMIT 1;
        IF(id IS NULL) THEN
            RETURN 0;
        ELSE
            RETURN id;
        END IF;
    END
$$
LANGUAGE 'plpgsql';