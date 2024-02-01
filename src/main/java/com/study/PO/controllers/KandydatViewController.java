package com.study.PO.controllers;

import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.entities.kierunek.StopienStudiow;
import com.study.PO.entities.kierunek.wskaznik.Rodzaje_kryteriow;
import com.study.PO.entities.wniosek.StatusWniosku;
import com.study.PO.entities.wniosek.Wniosek;
import com.study.PO.entities.wniosek.dane.*;
import com.study.PO.services.KandydatService;
import com.study.PO.services.KierunekService;
import com.study.PO.services.WniosekService;
import com.study.PO.services.WydzialService;
import com.study.PO.viewModels.PU7wynikiInzynierskieViewModel;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class KandydatViewController {

    @Autowired
    KierunekService kierunekService;

    @Autowired
    KandydatService kandydatService;

    @Autowired
    WniosekService wniosekService;

    @GetMapping("/kandydat/")
    public String getMainPage() {
        return "kandydat/main";
    }

    @GetMapping("/kandydat/defwniosku/danepersonalne")
    public String getPU5DanePersonalne(DanePersonalne danePersonalne) {
        return "kandydat/PU5_dane_personalne";
    }

    @PostMapping("/kandydat/defwniosku/danepersonalne")
    public String postPU5DanePersonalne(@Valid DanePersonalne danePersonalne, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "kandydat/PU5_dane_personalne";
        }
        session.setAttribute("danePersonalne", danePersonalne);
        return "redirect:/kandydat/defwniosku/wyborstopnia";
    }

    @GetMapping("/kandydat/defwniosku/wyborstopnia")
    public String getPU7WyborStopnia() {
        return "kandydat/PU5_wybor_stopnia";
    }

    @PostMapping("/kandydat/defwniosku/wyborstopnia")
    public String postPU7WyborStopnia(@RequestParam int stopienStudiow, HttpSession session) {
        StopienStudiow stStud = stopienStudiow == 1 ? StopienStudiow.I : (stopienStudiow == 2 ? StopienStudiow.II : StopienStudiow.III);
        session.setAttribute("stopienStudiow", stStud);
        return "redirect:/kandydat/defwniosku/wyborkierunku";
    }

    @GetMapping("/kandydat/defwniosku/wyborkierunku")
    public String getPU5WyborKierunku(Model model, HttpSession session) {
        if (session.getAttribute("stopienStudiow") == null)
            return "redirect:/kandydat/defwniosku/danepersonalne";
        StopienStudiow stopStud = (StopienStudiow) session.getAttribute("stopienStudiow");
        List<Kierunek> kierunki = kierunekService.getAllKierunekByStopienStudiow(stopStud.name());
        List<String> nazwyKierunkow =
                kierunki.stream()
                .map(Kierunek::getNazwa)
                .toList();
        model.addAttribute("nazwyKierunkow", nazwyKierunkow);
        return "kandydat/PU5_wybor_kierunku";
    }

    @PostMapping("/kandydat/defwniosku/wyborkierunku")
    public String postPU5WyborKierunku(@RequestParam String nazwaKierunku, HttpSession session) {
        session.setAttribute("kierunek", nazwaKierunku);
        return "redirect:/kandydat/defwniosku/danedodatkowe";
    }

    @GetMapping("/kandydat/defwniosku/danedodatkowe")
    public String getPU5DaneDodatkowe(Model model, HttpSession session) {
        if (session.getAttribute("stopienStudiow") == null || session.getAttribute("kierunek") == null)
            return "redirect:/kandydat/defwniosku/danepersonalne";
        StopienStudiow stopStud = (StopienStudiow) session.getAttribute("stopienStudiow");

        switch (stopStud) {
            case I -> {
                model.addAttribute("daneDodatkowe", new DaneDodatkoweInz());
                model.addAttribute("stopienStudiow", 1);
            }
            case II -> {
                model.addAttribute("daneDodatkowe", new DaneDodatkoweMag());
                model.addAttribute("stopienStudiow", 2);
            }
            case III -> {
                model.addAttribute("daneDodatkowe", new DaneDodatkoweDok());
                model.addAttribute("stopienStudiow", 3);
            }
        }
        return "kandydat/PU5_dane_dodatkowe";
    }

    @PostMapping("/kandydat/defwniosku/danedodatkowe/inz")
    public String postPU5DaneDodatkowe(@Valid DaneDodatkoweInz daneDodatkowe, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "kandydat/PU5_dane_dodatkowe";
        }
        session.setAttribute("daneDodatkowe", daneDodatkowe);
        return "redirect:/kandydat/defwniosku/dolaczaniedokumentow";
    }

    @PostMapping("/kandydat/defwniosku/danedodatkowe/mag")
    public String postPU5DaneDodatkowe(@Valid DaneDodatkoweMag daneDodatkowe, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "kandydat/PU5_dane_dodatkowe";
        }
        session.setAttribute("daneDodatkowe", daneDodatkowe);
        return "redirect:/kandydat/defwniosku/dolaczaniedokumentow";
    }

    @PostMapping("/kandydat/defwniosku/danedodatkowe/dok")
    public String postPU5DaneDodatkowe(@Valid DaneDodatkoweDok daneDodatkowe, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "kandydat/PU5_dane_dodatkowe";
        }
        session.setAttribute("daneDodatkowe", daneDodatkowe);
        return "redirect:/kandydat/defwniosku/dolaczaniedokumentow";
    }

    @GetMapping("/kandydat/defwniosku/dolaczaniedokumentow")
    public String getPU5DolaczanieDokumentow() {
        return "kandydat/PU5_dolaczanie_dokumentow";
    }

    @GetMapping("/kandydat/defwniosku/potwierdzenie")
    public String getPU5Potwierdzenie() {
        return "kandydat/PU5_potwierdzenie";
    }

    @PostMapping("/kandydat/defwniosku/potwierdzenie")
    public String postPU5Potwierdzenie(HttpSession session) {
        if (session.getAttribute("danePersonalne") == null || session.getAttribute("daneDodatkowe") == null || session.getAttribute("kierunek") == null || session.getAttribute("stopienStudiow") == null)
            return "redirect:/kandydat/defwniosku/danepersonalne";

        StopienStudiow stopStud = (StopienStudiow) session.getAttribute("stopienStudiow");
        DaneDodatkowe daneDodatkowe = new DaneDodatkoweInz();
        switch (stopStud) {
            case I -> {
                daneDodatkowe = (DaneDodatkoweInz) session.getAttribute("daneDodatkowe");
            }
            case II -> {
                daneDodatkowe = (DaneDodatkoweMag) session.getAttribute("daneDodatkowe");
            }
            case III -> {
                daneDodatkowe = (DaneDodatkoweDok) session.getAttribute("daneDodatkowe");
            }
        }

        DanePersonalne danePersonalne = (DanePersonalne) session.getAttribute("danePersonalne");
        String nazwaKierunku = (String) session.getAttribute("kierunek");
        List<Kierunek> kierunki = kierunekService.getAllKierunek();
        Kierunek kierunek = kierunki.stream().filter(kier -> Objects.equals(kier.getNazwa(), nazwaKierunku) && kier.getStopienStudiow() == stopStud).toList().get(0);

        Wniosek w = new Wniosek();

        w.setKandydat(kandydatService.getKandydat(1L));
        w.setStatusWniosku(StatusWniosku.Zlozony);
        w.setDanePersonalne(danePersonalne);
        w.setDaneDodatkowe(daneDodatkowe);
        w.setKierunek(kierunek);

        wniosekService.addWniosek(w);
        return "redirect:/kandydat/";
    }

}
