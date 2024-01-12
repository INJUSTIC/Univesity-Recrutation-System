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
    (85.0, 0, 0, 'Gospodarka przestrzenna', 'sciezka_do_planu_studiow', 0, 60, 'I', 2, 1),
    (150.0, 0, 0, 'Budownictwo', 'sciezka_do_planu_studiow', 0, 510, 'I', 3, 2);