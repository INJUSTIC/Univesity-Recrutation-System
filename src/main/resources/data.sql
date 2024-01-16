USE recrutation_system_db;

INSERT INTO wydzialy (nazwa, skrót) VALUES
    ('Wydział Architektury', 'W01'),
    ('Wydział Budownictwa Lądowego i Wodnego', 'W02'),
    ('Wydział Chemiczny', 'W03'),
    ('Wydział Informatyki i Telekomunikacji', 'W04'),
    ('Wydział Elektryczny', 'W05'),
    ('Wydział Geoinżynierii, Górnictwa i Geologii', 'W06'),
    ('Wydział Inżynierii Środowiska', 'W07'),
    ('Wydział Zarządzania', 'W08'),
    ('Wydział Mechaniczno-Energetyczny', 'W09'),
    ('Wydział Mechaniczny', 'W10'),
    ('Wydział Podstawowych Problemów Techniki', 'W11'),
    ('Wydział Elektroniki, Fotoniki i Mikrosystemów', 'W12'),
    ('Wydział Matematyki', 'W13'),
    ('Wydział Medyczny', 'W14');

INSERT INTO opiekunowie (imię, nazwisko) VALUES
    ('Dariusz', 'Wojciechowski'),
    ('Kazimierz', 'Jaskuła'),
    ('Anna', 'Zakrzewska'),
    ('Piotr', 'Jarosz'),
    ('Jacek', 'Wróblewski'),
    ('Magdalena', 'Smolarek'),
    ('Agata', 'Ziętek'),
    ('Kamil', 'Bartoszewski'),
    ('Adam', 'Kubiak'),
    ('Lucyna', 'Mazurek'),
    ('Jagoda', 'Górska'),
    ('Marlena', 'Chmielewska'),
    ('Witold', 'Michalik'),
    ('Jarosław', 'Kowalczyk');


INSERT INTO kierunki (cena_za_wniosek, liczba_chetnych, liczba_os_na_miejsce, nazwa, plan_studiow, prog_punktowy, progn_liczba_miejsc, stopien_studiow, opiekun_id, wydzial_id) VALUES
    (150.0, 0, 0, 'Architektura', 'sciezka_do_planu_studiow', 0, 180, 'I', 1, 1),
    (85.0, 0, 0, 'Budownictwo', 'sciezka_do_planu_studiow', 0, 510, 'I', 2, 2),
    (85.0, 0, 0, 'Inżynieria materiałowa', 'sciezka_do_planu_studiow', 0, 70, 'I', 3, 3),
    (85.0, 0, 0, 'Informatyka stosowana', 'sciezka_do_planu_studiow', 0, 120, 'I', 4, 4),
    (85.0, 0, 0, 'Elektromobilność', 'sciezka_do_planu_studiow', 0, 120, 'I', 5, 5),
    (85.0, 0, 0, 'Inżynieria surowców mineralnych', 'sciezka_do_planu_studiow', 0, 45, 'I', 6, 6),
    (85.0, 0, 0, 'Inżynieria środowiska', 'sciezka_do_planu_studiow', 0, 120, 'I', 7, 7),
    (85.0, 0, 0, 'Zarządzanie', 'sciezka_do_planu_studiow', 0, 120, 'I', 8, 8),
    (85.0, 0, 0, 'Lotnictwo i kosmonautyka', 'sciezka_do_planu_studiow', 0, 120, 'I', 9, 9),
    (85.0, 0, 0, 'Mechanika i budowa maszyn', 'sciezka_do_planu_studiow', 0, 240, 'I', 10, 10),
    (85.0, 0, 0, 'Inżynieria kwantowa', 'sciezka_do_planu_studiow', 0, 70, 'I', 11, 11),
    (85.0, 0, 0, 'Automatyka i robotyka', 'sciezka_do_planu_studiow', 0, 80, 'I', 12, 12),
    (85.0, 0, 0, 'Matematyka i analiza danych', 'sciezka_do_planu_studiow', 0, 50, 'I', 13, 13),
    (85.0, 0, 0, 'Lekarski', 'sciezka_do_planu_studiow', 0, 60, 'I', 14, 14);

-- Insert data into kryteria
INSERT INTO kryteria (Nazwa) VALUES
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA'),
    ('MATURA_POLSKA');

-- Insert data into maturalne_przedmioty_dodatkowe
INSERT INTO maturalne_przedmioty_dodatkowe (id, czy_fizyka, czy_biologia, czy_chemia, czy_geografia, czy_informatyka) VALUES
    (1, false, false, false, false, false),
    (2, false, false, false, false, true),
    (3, false, false, false, true, false),
    (4, false, false, false, true, true),
    (5, false, false, true, false, false),
    (6, false, false, true, false, true),
    (7, false, false, true, true, false),
    (8, false, false, true, true, true),
    (9, false, true, false, false, false),
    (10, false, true, false, false, true),
    (11, false, true, false, true, false),
    (12, false, true, false, true, true),
    (13, false, true, true, false, false),
    (14, false, true, true, false, true),
    (15, false, true, true, true, false),
    (16, false, true, true, true, true),
    (17, true, false, false, false, false),
    (18, true, false, false, false, true),
    (19, true, false, false, true, false),
    (20, true, false, false, true, true),
    (21, true, false, true, false, false),
    (22, true, false, true, false, true),
    (23, true, false, true, true, false),
    (24, true, false, true, true, true),
    (25, true, true, false, false, false),
    (26, true, true, false, false, true),
    (27, true, true, false, true, false),
    (28, true, true, false, true, true),
    (29, true, true, true, false, false),
    (30, true, true, true, false, true),
    (31, true, true, true, true, false),
    (32, true, true, true, true, true);

-- Insert data into kryteria_wstepne
INSERT INTO kryteria_wstepne (kierunek_id, kryterium_id) VALUES
(1,17),
(2,17),
(3,17),
(4,18),
(5,17),
(6,23),
(7,17),
(8,18),
(9,17),
(10,17),
(11,17),
(12,17),
(13,18),
(14,21);

-- Insert data into kandydaci
INSERT INTO kandydaci (login, haslo) VALUES
('john_doe', 'password123'),
('jane_smith', 'pass123'),
('bob_jones', 'securePass'),
('alice_wonderland', 'wonderPass'),
('charlie_brown', 'brownPass');


INSERT INTO dane_dodatkowe (id) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12);
-- Insert data into DaneDodatkoweDok
INSERT INTO dane_dodatkowe_doktor (id, data_uk_stud_II_stop, kierunek_ukoncz_stud_II_stop, uczelnia_ukoncz_stud_II_stop)
VALUES (1,'2022-01-01', 'Computer Science', 'University A');

-- Insert data into DaneDodatkoweInz
INSERT INTO dane_dodatkowe_inz (id, data_uk_szk_sr, nr_swiad_dojrz, data_wyst_swiad_dojrz, uczelnia_ukoncz_szk_sr)
VALUES (2,'2021-01-01', '12345', '2021-02-01', 'High School B');

-- Insert data into DaneDodatkoweMag
INSERT INTO dane_dodatkowe_mag (id, data_uk_stud_I_stop, kierunek_ukoncz_stud_I_stop, uczelnia_ukoncz_stud_I_stop)
VALUES (3,'2020-01-01', 'Computer Engineering', 'University C');

INSERT INTO dane_dodatkowe_mag (id, data_uk_stud_I_stop, kierunek_ukoncz_stud_I_stop, uczelnia_ukoncz_stud_I_stop)
VALUES (4,'2020-01-01', 'Computer Engineering', 'University C');

INSERT INTO dane_dodatkowe_mag (id, data_uk_stud_I_stop, kierunek_ukoncz_stud_I_stop, uczelnia_ukoncz_stud_I_stop)
VALUES (5,'2020-01-01', 'Computer Engineering', 'University C');

INSERT INTO dane_dodatkowe_mag (id, data_uk_stud_I_stop, kierunek_ukoncz_stud_I_stop, uczelnia_ukoncz_stud_I_stop)
VALUES (6,'2020-01-01', 'Computer Engineering', 'University C');

INSERT INTO dane_dodatkowe_doktor (id, data_uk_stud_II_stop, kierunek_ukoncz_stud_II_stop, uczelnia_ukoncz_stud_II_stop)
VALUES (7,'2022-01-01', 'Computer Science', 'University A');

-- Insert data into DaneDodatkoweInz
INSERT INTO dane_dodatkowe_inz (id, data_uk_szk_sr, nr_swiad_dojrz, data_wyst_swiad_dojrz, uczelnia_ukoncz_szk_sr)
VALUES (8,'2021-01-01', '12345', '2021-02-01', 'High School B');

-- Insert data into DaneDodatkoweMag
INSERT INTO dane_dodatkowe_mag (id, data_uk_stud_I_stop, kierunek_ukoncz_stud_I_stop, uczelnia_ukoncz_stud_I_stop)
VALUES (9,'2020-01-01', 'Computer Engineering', 'University C');

INSERT INTO dane_dodatkowe_mag (id, data_uk_stud_I_stop, kierunek_ukoncz_stud_I_stop, uczelnia_ukoncz_stud_I_stop)
VALUES (10,'2020-01-01', 'Computer Engineering', 'University C');

INSERT INTO dane_dodatkowe_mag (id, data_uk_stud_I_stop, kierunek_ukoncz_stud_I_stop, uczelnia_ukoncz_stud_I_stop)
VALUES (11,'2020-01-01', 'Computer Engineering', 'University C');

INSERT INTO dane_dodatkowe_mag (id, data_uk_stud_I_stop, kierunek_ukoncz_stud_I_stop, uczelnia_ukoncz_stud_I_stop)
VALUES (12,'2020-01-01', 'Computer Engineering', 'University C');


-- Insert data into DanePersonalne
INSERT INTO dane_personalne (imie, nazwisko, kraj_ur, email, data_ur, miejsc_ur, imie_matki, imie_ojca,
                             kraj_zam, miejsc_zam, ulica, numer_domu, numer_mieszk, kod_pocztowy, powiat, wojewodz, numer_tel,
                             obywatelstwo, pesel, numer_paszp)
VALUES ('John', 'Doe', 0, 'john.doe@example.com', '1990-01-01', 'Warsaw', 'Mary', 'John Sr.',
        0, 'Warsaw', 'Main St', '123', '45', '00-001', 'Mazowieckie', 'Mazowieckie', '123456789',
        'Polish', '12345678901', 'AB123456');


-- Insert data into dane_personalne
INSERT INTO dane_personalne (imie, nazwisko, kraj_ur, email, data_ur, miejsc_ur, imie_matki, imie_ojca,
                             kraj_zam, miejsc_zam, ulica, numer_domu, numer_mieszk, kod_pocztowy, powiat, wojewodz, numer_tel,
                             obywatelstwo, pesel, numer_paszp)
VALUES ('Vlad', 'Portnov', 1, 'john.doe@example.com', '1990-01-01', 'Warsaw', 'Mary', 'John Sr.',
        0, 'Warsaw', 'Main St', '123', '45', '00-001', 'Mazowieckie', 'Mazowieckie', '123456789',
        'Polish', '12345678901', 'AB123456');

-- Insert data into dane_personalne
INSERT INTO dane_personalne (imie, nazwisko, kraj_ur, email, data_ur, miejsc_ur, imie_matki, imie_ojca,
                             kraj_zam, miejsc_zam, ulica, numer_domu, numer_mieszk, kod_pocztowy, powiat, wojewodz, numer_tel,
                             obywatelstwo, pesel, numer_paszp)
VALUES ('Name', 'Surname', 2, 'john.doe@example.com', '1990-01-01', 'Warsaw', 'Mary', 'John Sr.',
        0, 'Warsaw', 'Main St', '123', '45', '00-001', 'Mazowieckie', 'Mazowieckie', '123456789',
        'Polish', '12345678901', 'AB123456');

INSERT INTO dane_personalne (imie, nazwisko, kraj_ur, email, data_ur, miejsc_ur, imie_matki, imie_ojca,
                             kraj_zam, miejsc_zam, ulica, numer_domu, numer_mieszk, kod_pocztowy, powiat, wojewodz, numer_tel,
                             obywatelstwo, pesel, numer_paszp)
VALUES ('John', 'Doe', 0, 'john.doe@example.com', '1990-01-01', 'Warsaw', 'Mary', 'John Sr.',
        0, 'Warsaw', 'Main St', '123', '45', '00-001', 'Mazowieckie', 'Mazowieckie', '123456789',
        'Polish', '12345678901', 'AB123456');


-- Insert data into dane_personalne
INSERT INTO dane_personalne (imie, nazwisko, kraj_ur, email, data_ur, miejsc_ur, imie_matki, imie_ojca,
                             kraj_zam, miejsc_zam, ulica, numer_domu, numer_mieszk, kod_pocztowy, powiat, wojewodz, numer_tel,
                             obywatelstwo, pesel, numer_paszp)
VALUES ('Vlad', 'Portnov', 1, 'john.doe@example.com', '1990-01-01', 'Warsaw', 'Mary', 'John Sr.',
        0, 'Warsaw', 'Main St', '123', '45', '00-001', 'Mazowieckie', 'Mazowieckie', '123456789',
        'Polish', '12345678901', 'AB123456');

-- Insert data into dane_personalne
INSERT INTO dane_personalne (imie, nazwisko, kraj_ur, email, data_ur, miejsc_ur, imie_matki, imie_ojca,
                             kraj_zam, miejsc_zam, ulica, numer_domu, numer_mieszk, kod_pocztowy, powiat, wojewodz, numer_tel,
                             obywatelstwo, pesel, numer_paszp)
VALUES ('Name', 'Surname', 2, 'john.doe@example.com', '1990-01-01', 'Warsaw', 'Mary', 'John Sr.',
        0, 'Warsaw', 'Main St', '123', '45', '00-001', 'Mazowieckie', 'Mazowieckie', '123456789',
        'Polish', '12345678901', 'AB123456');

INSERT INTO dane_personalne (imie, nazwisko, kraj_ur, email, data_ur, miejsc_ur, imie_matki, imie_ojca,
                             kraj_zam, miejsc_zam, ulica, numer_domu, numer_mieszk, kod_pocztowy, powiat, wojewodz, numer_tel,
                             obywatelstwo, pesel, numer_paszp)
VALUES ('John', 'Doe', 0, 'john.doe@example.com', '1990-01-01', 'Warsaw', 'Mary', 'John Sr.',
        0, 'Warsaw', 'Main St', '123', '45', '00-001', 'Mazowieckie', 'Mazowieckie', '123456789',
        'Polish', '12345678901', 'AB123456');


-- Insert data into dane_personalne
INSERT INTO dane_personalne (imie, nazwisko, kraj_ur, email, data_ur, miejsc_ur, imie_matki, imie_ojca,
                             kraj_zam, miejsc_zam, ulica, numer_domu, numer_mieszk, kod_pocztowy, powiat, wojewodz, numer_tel,
                             obywatelstwo, pesel, numer_paszp)
VALUES ('Vlad', 'Portnov', 1, 'john.doe@example.com', '1990-01-01', 'Warsaw', 'Mary', 'John Sr.',
        0, 'Warsaw', 'Main St', '123', '45', '00-001', 'Mazowieckie', 'Mazowieckie', '123456789',
        'Polish', '12345678901', 'AB123456');

-- Insert data into dane_personalne
INSERT INTO dane_personalne (imie, nazwisko, kraj_ur, email, data_ur, miejsc_ur, imie_matki, imie_ojca,
                             kraj_zam, miejsc_zam, ulica, numer_domu, numer_mieszk, kod_pocztowy, powiat, wojewodz, numer_tel,
                             obywatelstwo, pesel, numer_paszp)
VALUES ('Name', 'Surname', 2, 'john.doe@example.com', '1990-01-01', 'Warsaw', 'Mary', 'John Sr.',
        0, 'Warsaw', 'Main St', '123', '45', '00-001', 'Mazowieckie', 'Mazowieckie', '123456789',
        'Polish', '12345678901', 'AB123456');

INSERT INTO dane_personalne (imie, nazwisko, kraj_ur, email, data_ur, miejsc_ur, imie_matki, imie_ojca,
                             kraj_zam, miejsc_zam, ulica, numer_domu, numer_mieszk, kod_pocztowy, powiat, wojewodz, numer_tel,
                             obywatelstwo, pesel, numer_paszp)
VALUES ('John', 'Doe', 0, 'john.doe@example.com', '1990-01-01', 'Warsaw', 'Mary', 'John Sr.',
        0, 'Warsaw', 'Main St', '123', '45', '00-001', 'Mazowieckie', 'Mazowieckie', '123456789',
        'Polish', '12345678901', 'AB123456');


-- Insert data into dane_personalne
INSERT INTO dane_personalne (imie, nazwisko, kraj_ur, email, data_ur, miejsc_ur, imie_matki, imie_ojca,
                             kraj_zam, miejsc_zam, ulica, numer_domu, numer_mieszk, kod_pocztowy, powiat, wojewodz, numer_tel,
                             obywatelstwo, pesel, numer_paszp)
VALUES ('Vlad', 'Portnov', 1, 'john.doe@example.com', '1990-01-01', 'Warsaw', 'Mary', 'John Sr.',
        0, 'Warsaw', 'Main St', '123', '45', '00-001', 'Mazowieckie', 'Mazowieckie', '123456789',
        'Polish', '12345678901', 'AB123456');

-- Insert data into dane_personalne
INSERT INTO dane_personalne (imie, nazwisko, kraj_ur, email, data_ur, miejsc_ur, imie_matki, imie_ojca,
                             kraj_zam, miejsc_zam, ulica, numer_domu, numer_mieszk, kod_pocztowy, powiat, wojewodz, numer_tel,
                             obywatelstwo, pesel, numer_paszp)
VALUES ('Name', 'Surname', 2, 'john.doe@example.com', '1990-01-01', 'Warsaw', 'Mary', 'John Sr.',
        0, 'Warsaw', 'Main St', '123', '45', '00-001', 'Mazowieckie', 'Mazowieckie', '123456789',
        'Polish', '12345678901', 'AB123456');



INSERT INTO wnioski (status_wniosku, id_dane_personalne, id_dane_dodatkowe, kierunek_id, kandydat_id)
VALUES ('Przyjety', 1, 1, 1, 1);

INSERT INTO wnioski (status_wniosku, id_dane_personalne, id_dane_dodatkowe, kierunek_id, kandydat_id)
VALUES ('OczekNaDecyzje', 2, 2, 2, 2);

INSERT INTO wnioski (status_wniosku, id_dane_personalne, id_dane_dodatkowe, kierunek_id, kandydat_id)
VALUES ('Niekompletny', 3, 3, 3, 3);

INSERT INTO wnioski (status_wniosku, id_dane_personalne, id_dane_dodatkowe, kierunek_id, kandydat_id)
VALUES ('Niekompletny', 4, 4, 3, 3);

INSERT INTO wnioski (status_wniosku, id_dane_personalne, id_dane_dodatkowe, kierunek_id, kandydat_id)
VALUES ('Przyjety', 5, 5, 3, 3);

INSERT INTO wnioski (status_wniosku, id_dane_personalne, id_dane_dodatkowe, kierunek_id, kandydat_id)
VALUES ('Przyjety', 6, 6, 2, 3);

INSERT INTO wnioski (status_wniosku, id_dane_personalne, id_dane_dodatkowe, kierunek_id, kandydat_id)
VALUES ('Przyjety', 7, 7, 1, 1);

INSERT INTO wnioski (status_wniosku, id_dane_personalne, id_dane_dodatkowe, kierunek_id, kandydat_id)
VALUES ('OczekNaDecyzje', 8, 8, 2, 2);

INSERT INTO wnioski (status_wniosku, id_dane_personalne, id_dane_dodatkowe, kierunek_id, kandydat_id)
VALUES ('Niekompletny', 9, 9, 3, 3);

INSERT INTO wnioski (status_wniosku, id_dane_personalne, id_dane_dodatkowe, kierunek_id, kandydat_id)
VALUES ('Niekompletny', 10, 10, 3, 3);

INSERT INTO wnioski (status_wniosku, id_dane_personalne, id_dane_dodatkowe, kierunek_id, kandydat_id)
VALUES ('Przyjety', 11, 11, 3, 3);

INSERT INTO wnioski (status_wniosku, id_dane_personalne, id_dane_dodatkowe, kierunek_id, kandydat_id)
VALUES ('Przyjety', 12, 12, 2, 3);


-- Insert data into dokumenty
INSERT INTO dokumenty (przelicznik, czy_zweryfikowany, typ_dokumentu, nazwa_dokumentu, wniosek_id)
VALUES (1.5, true, 'EGZAMIN_ZAGRANICZNY', 'Wniosek1.pdf', 1);

INSERT INTO dokumenty (przelicznik, czy_zweryfikowany, typ_dokumentu, nazwa_dokumentu, wniosek_id)
VALUES (2.0, false, 'EGZAMIN_MATURALNY', 'Wniosek2.pdf', 2);

INSERT INTO dokumenty (przelicznik, czy_zweryfikowany, typ_dokumentu, nazwa_dokumentu, wniosek_id)
VALUES (1.8, true, 'EGZAMIN_WEWNETRZNY', 'Wniosek3.pdf', 3);
