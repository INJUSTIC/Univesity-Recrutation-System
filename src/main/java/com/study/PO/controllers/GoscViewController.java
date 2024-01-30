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
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
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
    public String getPU7WyborStopnia() {
        return "gosc/PU7_wybor_stopnia";
    }

    @PostMapping("/gosc/obliczwskaznik")
    public String postPU7WyborStopnia(@RequestParam int stopienStudiow, HttpSession session) {
        StopienStudiow stStud = stopienStudiow == 1 ? StopienStudiow.I : StopienStudiow.II;
        session.setAttribute("stopienStudiow", stStud);
        return "redirect:/gosc/obliczwskaznik/kryteria";
    }

    @GetMapping("/gosc/obliczwskaznik/kryteria")
    public String getPU7Kryteria(Model model, HttpSession session){
        if (session.getAttribute("stopienStudiow") == null)
            return "redirect:/gosc/obliczwskaznik";
        StopienStudiow stopienStudiow = (StopienStudiow) session.getAttribute("stopienStudiow");
        if (stopienStudiow == StopienStudiow.I) {
            List<String> rodzajeKryteriow = kierunekService.getRodzajeKryteriowStopienStudiow(stopienStudiow);
            List<String> zmienioneRodzajeKryteriow = rodzajeKryteriow.stream()
                    .map(str -> str.toLowerCase().replace("_", " "))
                    .collect(Collectors.toList());
            model.addAttribute("rodzajeKryteriow", zmienioneRodzajeKryteriow);
        } else {

        }
        return "gosc/PU7_wybor_kryterium";
    }

    @PostMapping("/gosc/obliczwskaznik/kryteria")
    public String postPU7Kryteria(@RequestParam String kryterium, HttpSession session) {
        if (kryterium.equals("matura polska")) {
            session.setAttribute("kryterium", Rodzaje_kryteriow.MATURA_POLSKA);
        } else {
            if (kryterium.equals("lekarski")) {
                session.setAttribute("kryterium", Rodzaje_kryteriow.LEKARSKI);
            }
        }
        return "redirect:/gosc/obliczwskaznik/egzaminy";
    }

    @GetMapping("/gosc/obliczwskaznik/egzaminy")
    public String getPU7Egzaminy(Model model, HttpSession session) {
        if (session.getAttribute("kryterium") == null)
            return "redirect:/gosc/obliczwskaznik";
        Rodzaje_kryteriow kryterium = (Rodzaje_kryteriow) session.getAttribute("kryterium");
        List<String> nazwyEgzaminow = new ArrayList<>();

        if (kryterium == Rodzaje_kryteriow.MATURA_POLSKA || kryterium == Rodzaje_kryteriow.LEKARSKI) {
            for (MaturalnePrzedmiotyObowiazkowe przedmiot : MaturalnePrzedmiotyObowiazkowe.values()){
                nazwyEgzaminow.add(przedmiot.name().toLowerCase().replace("_", " ").replace("jezyk", "język"));
            }
            for (MaturalnePrzedmiotyDodatkowe przedmiot : MaturalnePrzedmiotyDodatkowe.values()){
                nazwyEgzaminow.add(przedmiot.name().toLowerCase().replace("_", " ").replace("jezyk", "język"));
            }
        } else {
            if (kryterium == Rodzaje_kryteriow.MATURA_IB) {
                nazwyEgzaminow.add("Jakis tam egzamin IB");
//                session.setAttribute("kryterium", Rodzaje_kryteriow.MATURA_IB.name().toLowerCase().replace('_', ' '));
            } else {
                nazwyEgzaminow.add("Jakis tam egzamin Matury zagranicznej");
            }
        }
        PU7wynikiInzynierskieViewModel viewModel = new PU7wynikiInzynierskieViewModel();
        model.addAttribute("viewModel", viewModel);
        session.setAttribute("viewModel", session);
        model.addAttribute("nazwyEgzaminow", nazwyEgzaminow);

        return "gosc/PU7_wybor_egzaminow";
    }

    @PostMapping("/gosc/obliczwskaznik/egzaminy")
    public String postPU7Egzaminy(@ModelAttribute("viewModel")PU7wynikiInzynierskieViewModel viewModel, Model model, HttpSession session) {
        if (session.getAttribute("viewModel") == null)
            return "redirect:/gosc/obliczwskaznik";
//        PU7wynikiInzynierskieViewModel sessionViewModel = (PU7wynikiInzynierskieViewModel) session.getAttribute("viewModel");
//        if (sessionViewModel.getWynikiPodstawa() == null || sessionViewModel.getWynikiRozszerzenie() == null) {
//            sessionViewModel.copyAttributes(viewModel);
//        }
        viewModel.initWyniki();

        session.setAttribute("viewModel", viewModel);
        return "redirect:/gosc/obliczwskaznik/I/wyniki";
    }

    @GetMapping("/gosc/obliczwskaznik/I/wyniki")
    public String getPU7Wyniki(Model model, HttpSession session) {
        if (session.getAttribute("viewModel") == null)
            return "redirect:/gosc/obliczwskaznik";
        PU7wynikiInzynierskieViewModel sessionViewModel = (PU7wynikiInzynierskieViewModel) session.getAttribute("viewModel");

        List<Kierunek> kierunki = kierunekService.getAllKierunek();
        Rodzaje_kryteriow kryterium = (Rodzaje_kryteriow) session.getAttribute("kryterium");
        List<String> nazwyKierunkow;
        if (kryterium == Rodzaje_kryteriow.LEKARSKI)
            nazwyKierunkow = Arrays.asList("Lekarski");
        else
            nazwyKierunkow = kierunki.stream()
                .map(Kierunek::getNazwa)
                .filter(nazwa -> !nazwa.equalsIgnoreCase("lekarski"))
                .toList();

//        List<String> nazwyKierunkow = kierunki.stream()
//                .map(k -> k.getNazwa())
//                .collect(Collectors.toList());
        model.addAttribute("nazwyKierunkow", nazwyKierunkow);
        if (session.getAttribute("kierunek") != null)
            model.addAttribute("kierunek", (String) session.getAttribute("kierunek"));
        else
            model.addAttribute("kierunek", "");
        model.addAttribute("viewModel", sessionViewModel);
        model.addAttribute("kryterium", (String) session.getAttribute("kryterium").toString());
        if (session.getAttribute("wskaznik") != null)
            model.addAttribute("wskaznik", (String) session.getAttribute("wskaznik"));
        return "gosc/PU7_wyniki_I_stopien";
    }

    @PostMapping("/gosc/obliczwskaznik/I/wyniki")
    public String postPU7Wskaznik(@ModelAttribute("viewModel")PU7wynikiInzynierskieViewModel pageViewModel, @RequestParam String nazwaKierunku, Model model, HttpSession session) {
        if (session.getAttribute("kryterium") == null || session.getAttribute("viewModel") == null)
            return "redirect:/gosc/obliczwskaznik";
        PU7wynikiInzynierskieViewModel viewModel = (PU7wynikiInzynierskieViewModel) session.getAttribute("viewModel");
        Rodzaje_kryteriow kryterium = (Rodzaje_kryteriow) session.getAttribute("kryterium");
        Kryterium krytObj = kierunekService.getKryteriumOnNazwaKierunkuRodzajKryterium(nazwaKierunku, kryterium);
        if (krytObj != null) {
            PrzelicznikKryterium przelicznik = PrzelicznikFactory.getPrzelicznik(krytObj);
            if (przelicznik != null) {
                viewModel.initWyniki();
                viewModel.setWynikiPodstawa(pageViewModel.getWynikiPodstawa());
                viewModel.setWynikiRozszerzenie(pageViewModel.getWynikiRozszerzenie());
                viewModel.setWynikEgzaminRysunek(pageViewModel.getWynikEgzaminRysunek());
                viewModel.setWynikStudiumTalentMatematyka(pageViewModel.getWynikStudiumTalentMatematyka());
                viewModel.setWynikStudiumTalentFizyka(pageViewModel.getWynikStudiumTalentFizyka());
                Map<String, Double> wyniki1 = viewModel.getWynikiPodstawa();
                Map<String, Double> wyniki2 = viewModel.getWynikiRozszerzenie();
                double wynik = przelicznik.przeliczKryterium(new Wyniki(wyniki1, wyniki2));
                if (viewModel.isCzyStudiumTalentFizyka() || viewModel.isCzyStudiumTalentMatematyka()){
                    double stFizyka = 0;
                    double stMatematyka = 0;
                    if (viewModel.isCzyStudiumTalentFizyka())
                        stFizyka = viewModel.getWynikStudiumTalentFizyka();
                    if (viewModel.isCzyStudiumTalentMatematyka())
                        stMatematyka = viewModel.getWynikStudiumTalentMatematyka();
                    double studium = Math.max(stFizyka, stMatematyka);
                    if (studium >= 3.0 && studium < 4.0)
                        wynik = Math.max(535.0, wynik + 30.0);
                    else {
                        if (studium >= 4.0 && studium < 5.0)
                            wynik = Math.max(535.0, wynik + 40.0);
                        else {
                            if (studium >= 5.0)
                                wynik = 535.0;
                        }
                    }
                }
                if (nazwaKierunku.equalsIgnoreCase("architektura") && viewModel.isCzyEgzaminRysunek())
                    wynik += viewModel.getWynikEgzaminRysunek();
                String wynikString = String.format("%.2f", wynik);
                session.setAttribute("wskaznik", wynikString);
            }
        }
        session.setAttribute("viewModel", viewModel);
        session.setAttribute("kierunek", nazwaKierunku);
        return "redirect:/gosc/obliczwskaznik/I/wyniki";
    }

//    @PostMapping("/gosc/obliczwskaznik/I/wyniki")
//    public String getPU7Wyniki(@ModelAttribute("viewModel") PU7wynikiInzynierskieViewModel viewModel, Model model) {
//        viewModel.initWyniki();
//        List<Kierunek> kierunki = kierunekService.getAllKierunek();
//        List<String> nazwyKierunkow = kierunki.stream()
//                .map(k -> k.getNazwa())
//                .collect(Collectors.toList());
//        model.addAttribute("nazwyKierunkow", nazwyKierunkow);
//        model.addAttribute("viewModel", viewModel);
//        return "gosc/PU7_wyniki_I_stopien";
//    }
//
//    @PostMapping("/gosc/obliczwskaznik/I/wskaznik")
//    public String getPU7Wskaznik(@ModelAttribute("viewModel") PU7wynikiInzynierskieViewModel viewModel, @RequestParam String nazwaKierunku, Model model) {
//        Kryterium kryterium = kierunekService.getKryteriumOnNazwaKierunkuRodzajKryterium(nazwaKierunku, Rodzaje_kryteriow.MATURA_POLSKA);
//        if (kryterium != null) {
//            PrzelicznikKryterium przelicznik = PrzelicznikFactory.getPrzelicznik(kryterium);
//            if (przelicznik != null) {
//                double wynik = przelicznik.przeliczKryterium(new Wyniki(viewModel.getWynikiPodstawa(), viewModel.getWynikiRozszerzenie()));
//                model.addAttribute("wskaznik", wynik);
//            }
//        }
//        model.addAttribute("viewModel", viewModel);
//        return "gosc/PU7_wyniki_I_stopien";
//    }

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
