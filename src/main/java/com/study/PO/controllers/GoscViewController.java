package com.study.PO.controllers;

import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.entities.kierunek.StopienStudiow;
import com.study.PO.services.KierunekService;
import com.study.PO.viewModels.PU7wyborEgzaminowMaturalnychViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class GoscViewController {

    private final KierunekService kierunekService;

    @Autowired
    public GoscViewController(KierunekService kierunekService) {
        this.kierunekService = kierunekService;
    }


    @GetMapping("/gosc/")
    public String getMainPage() {
        return "gosc/main";
    }

    @GetMapping("/gosc/obliczwskaznik")
    public String getObliczanieWskaznika() {
        return "gosc/PU7_wybor_stopnia";
    }

    @PostMapping("/gosc/obliczwskaznik")
    public String getPU7StopienStudiow(@RequestParam int stopienStudiow, Model model) {
        StopienStudiow stStud = stopienStudiow == 1 ? StopienStudiow.I : StopienStudiow.II;
        List<String> rodzajeKryteriow = kierunekService.getRodzajeKryteriowStopienStudiow(stStud);
        List<String> zmienioneRodzajeKryteriow = rodzajeKryteriow.stream()
                .map(str -> str.toLowerCase().replace("_", " "))
                .collect(Collectors.toList());
        model.addAttribute("rodzajeKryteriow", zmienioneRodzajeKryteriow);

        return "gosc/PU7_wybor_kryterium";
    }

    @PostMapping("/gosc/obliczwskaznik/kryteria")
    public String getPU7Kryteria(@RequestParam String kryterium, Model model) {
        List<String> nazwyEgzaminow = new ArrayList<>();
        if (kryterium.equals("matura polska"))
            nazwyEgzaminow = List.of("Matematyka", "Fizyka", "Chemia", "Biologia", "Geografia", "Informatyka", "Język obcy", "Jęzzyk polski");
        model.addAttribute("nazwyEgzaminow", nazwyEgzaminow);
        model.addAttribute("viewModel", new PU7wyborEgzaminowMaturalnychViewModel());
        return "gosc/PU7_wybor_egzaminow";
    }

    @PostMapping("/gosc/obliczwskaznik/egzaminy")
    public String getPU7Egzaminy(@ModelAttribute("viewModel") PU7wyborEgzaminowMaturalnychViewModel viewModel, Model model) {
        Set<String> nazwyEgzaminow = new HashSet<>(viewModel.getWybraneEgzaminyPodstawa());
        for (String nazwaEgz : viewModel.getWybraneEgzaminyRozszerzenie()) {
            nazwyEgzaminow.add(nazwaEgz);
        }
        model.addAttribute("nazwyEgzaminow", nazwyEgzaminow);
        return "gosc/PU7_wprowadzenie_wynikow";
    }

}
