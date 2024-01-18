package com.study.PO.controllers;

import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.entities.kierunek.StopienStudiow;
import com.study.PO.entities.kierunek.wskaznik.*;
import com.study.PO.entities.wydzial.Wydzial;
import com.study.PO.services.KierunekService;
import com.study.PO.viewModels.PU7stopienStudiow;
import com.study.PO.services.WydzialService;
import com.study.PO.viewModels.PU7wyborEgzaminowMaturalnychViewModel;
import com.study.PO.viewModels.PU7wynikiInzynierskieViewModel;
import com.study.PO.viewModels.PU7wynikiMagisterskieViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class GoscViewController {

    @Autowired
    WydzialService wydzialService;

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

    @GetMapping("/gosc/przegladKierunki")
    public String przegladKierunki(Model model) {
        model.addAttribute("wydzialy",wydzialService.getAllWydzial());
        return "gosc/PU2_wszystkie_wydzialy_page";
    }
    @GetMapping("/gosc/kierunki/{id}")
    public String getWydzialy(Model model, @PathVariable long id) {
        Wydzial wydzial = wydzialService.getWydzial(id);
        model.addAttribute("kierunki",wydzial.getKierunki());
        return "gosc/PU2_wszystkie_kierunki_page";
    }

    @GetMapping("/gosc/przegladajkierunek/{id}")
    public String przegladajKierunek(Model model, @PathVariable long id) {
        Kierunek kierunek = kierunekService.getKierunek(id);
        model.addAttribute("kierunek",kierunek);
        return "gosc/PU2_przegladaj_kierunek_page";
    }

    @GetMapping("/gosc/kierunekproghist/{id}")
    public String przegladajProgHist(Model model, @PathVariable long id) {
        Kierunek kierunek = kierunekService.getKierunek(id);
        List<Integer> prog_hist = kierunek.getPrzeszProgi();
        List<Integer> osob_hist = kierunek.getLiczbaKandydWPoprzLat();
        List<Double> l_oso_na_miejsce = new ArrayList<>();
        List<Integer> lata = new ArrayList<>();

        for(int i = 0; i < prog_hist.size(); i++){
            double value = (double) ((double)osob_hist.get(i)/(double)kierunek.getPrognLiczbaMiejsc());
            BigDecimal bd = new BigDecimal(value).setScale(3, RoundingMode.HALF_UP);
            double rounded = bd.doubleValue();
            l_oso_na_miejsce.add(rounded);
            lata.add(2023 - i);
        }
        model.addAttribute("prog_hist",prog_hist);
        model.addAttribute("osob_hist",osob_hist);
        model.addAttribute("l_os_na_miejsce",l_oso_na_miejsce);
        model.addAttribute("lata",lata);
        model.addAttribute("kierunek",kierunekService.getKierunek(id));

        return "gosc/PU2_prog_hist_page";
    }

}
