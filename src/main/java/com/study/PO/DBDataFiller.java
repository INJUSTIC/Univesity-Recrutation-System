package com.study.PO;


import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.entities.wydzial.Wydzial;
import com.study.PO.repositories.KierunekRepository;
import com.study.PO.repositories.WydzialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Component
//public class DBDataFiller implements CommandLineRunner {

//    @Autowired
//    WydzialRepository wydzialRepo;
//    @Autowired
//    KierunekRepository kierunekRepo;
//    @Override
//    public void run(String... args) throws Exception {
//        wydzialRepo.saveAll(Arrays.asList(
//                new Wydzial("Wydział Architektury", "W01"),
//                new Wydzial("Wydział Budownictwa Lądowego i Wodnego", "W02"),
//                new Wydzial("Wydział Chemiczny", "W03"),
//                new Wydzial("Wydział Informatyki i Telekomunikacji", "W04"),
//                new Wydzial("Wydział Elektryczny", "W05"),
//                new Wydzial("Wydział Geoinżynierii, Górnictwa i Geologii", "W06"),
//                new Wydzial("Wydział Inżynierii Środowiska", "W07"),
//                new Wydzial("Wydział Zarządzania", "W08"),
//                new Wydzial("Wydział Mechaniczno-Energetyczny", "W09"),
//                new Wydzial("Wydział Mechaniczny", "W10"),
//                new Wydzial("Wydział Podstawowych Problemów Techniki", "W11"),
//                new Wydzial("Wydział Elektroniki, Fotoniki i Mikrosystemów", "W12"),
//                new Wydzial("Wydział Matematyki", "W13"),
//                new Wydzial("Wydział Medyczny", "W14")
//        ));
//
//        kierunekRepo.saveAll(Arrays.asList(
//                new Kierunek()
//        ));
//
//        System.out.println("----------'Wydzial' Data saved into Database----------------------");
//    }
//}
