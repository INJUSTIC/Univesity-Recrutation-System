package com.study.PO.controllers;

import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.entities.kierunek.StopienStudiow;
import com.study.PO.entities.kierunek.wskaznik.*;
import com.study.PO.services.KierunekService;
import com.study.PO.viewModels.PU7stopienStudiow;
import com.study.PO.viewModels.PU7wyborEgzaminowMaturalnychViewModel;
import com.study.PO.viewModels.PU7wynikiInzynierskieViewModel;
import com.study.PO.viewModels.PU7wynikiMagisterskieViewModel;
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
        model.addAttribute("stopienStudiow", stopienStudiow);

        if (stStud == StopienStudiow.I) {
            List<String> rodzajeKryteriow = kierunekService.getRodzajeKryteriowStopienStudiow(stStud);
            List<String> zmienioneRodzajeKryteriow = rodzajeKryteriow.stream()
                    .map(str -> str.toLowerCase().replace("_", " "))
                    .collect(Collectors.toList());
            model.addAttribute("rodzajeKryteriow", zmienioneRodzajeKryteriow);
            return "gosc/PU7_wybor_kryterium";
        }

        model.addAttribute("viewModel", new PU7wynikiMagisterskieViewModel());
        return "gosc/PU7_wyniki_II_stopien";
    }


    @PostMapping("/gosc/obliczwskaznik/kryteria")
    public String getPU7Kryteria(@RequestParam String kryterium, Model model) {
        List<String> nazwyEgzaminow = new ArrayList<>();
        if (kryterium.equals("matura polska")) {
            for (MaturalnePrzedmiotyObowiazkowe przedmiot : MaturalnePrzedmiotyObowiazkowe.values()){
                nazwyEgzaminow.add(przedmiot.name().toLowerCase().replace("_", " ").replace("jezyk", "język"));
            }
            for (MaturalnePrzedmiotyDodatkowe przedmiot : MaturalnePrzedmiotyDodatkowe.values()){
                nazwyEgzaminow.add(przedmiot.name().toLowerCase().replace("_", " ").replace("jezyk", "język"));
            }
            model.addAttribute("nazwyEgzaminow", nazwyEgzaminow);
        } else {
            if (kryterium.equals(Rodzaje_kryteriow.MATURA_IB.name())) {
                nazwyEgzaminow.add("Jakis tam egzamin IB");
                model.addAttribute("viewModel", new PU7wyborEgzaminowMaturalnychViewModel());
            } else {
                nazwyEgzaminow.add("Jakis tam egzamin Matury zagranicznej");
                model.addAttribute("viewModel", new PU7wyborEgzaminowMaturalnychViewModel());
            }
        }
        PU7wynikiInzynierskieViewModel vm =  new PU7wynikiInzynierskieViewModel();
        vm.setKryterium(Rodzaje_kryteriow.MATURA_POLSKA);
        model.addAttribute("viewModel", vm);
        return "gosc/PU7_wybor_egzaminow";
    }

//    @PostMapping("/gosc/obliczwskaznik/egzaminy")
//    public String getPU7Egzaminy(@ModelAttribute("viewModel") PU7wyborEgzaminowMaturalnychViewModel viewModel, Model model) {
//        Set<String> nazwyEgzaminow = new HashSet<>(viewModel.getWybraneEgzaminyPodstawa());
//        for (String nazwaEgz : viewModel.getWybraneEgzaminyRozszerzenie()) {
//            nazwyEgzaminow.add(nazwaEgz);
//        }
//        model.addAttribute("nazwyEgzaminow", nazwyEgzaminow);
//        return "gosc/PU7_wprowadzenie_wynikow";
//    }

    @PostMapping("/gosc/obliczwskaznik/I/wyniki")
    public String getPU7Wyniki(@ModelAttribute("viewModel") PU7wynikiInzynierskieViewModel viewModel, Model model) {
        viewModel.initWyniki();
        List<Kierunek> kierunki = kierunekService.getAllKierunek();
        List<String> nazwyKierunkow = kierunki.stream()
                .map(k -> k.getNazwa())
                .collect(Collectors.toList());
        model.addAttribute("nazwyKierunkow", nazwyKierunkow);
//        model.addAttribute("viewModel", viewModel);
        return "gosc/PU7_wyniki_I_stopien";
    }

    @PostMapping("/gosc/obliczwskaznik/I/wskaznik")
    public String getPU7Wskaznik(@ModelAttribute("viewModel") PU7wynikiInzynierskieViewModel viewModel, @RequestParam String nazwaKierunku, Model model) {
        Kryterium kryterium = kierunekService.getKryteriumOnNazwaKierunkuRodzajKryterium(nazwaKierunku, Rodzaje_kryteriow.MATURA_POLSKA);
        if (kryterium != null) {
            PrzelicznikKryterium przelicznik = PrzelicznikFactory.getPrzelicznik(kryterium);
            if (przelicznik != null) {
                double wynik = przelicznik.przeliczKryterium(new Wyniki(viewModel.getWynikiPodstawa(), viewModel.getWynikiRozszerzenie()));
                model.addAttribute("wskaznik", wynik);
            }
        }
//        model.addAttribute("viewModel", viewModel);
        return "gosc/PU7_wyniki_I_stopien";
    }

    @PostMapping("/gosc/obliczwskaznik/II/wyniki")
    public String getPU7WynikiMagisterskie(@ModelAttribute("viewModel") PU7wynikiMagisterskieViewModel viewModel, Model model) {
        double wynik = 10.0 * viewModel.getOcenaNaDyplomie() + viewModel.getSredniaZeStudiow() + viewModel.getWynikEgzaminu() + viewModel.getPrzelicznikStudiowInzynierskich();
        model.addAttribute("wskaznik", wynik);
        return "gosc/PU7_wyniki_II_stopien";
    }

}
