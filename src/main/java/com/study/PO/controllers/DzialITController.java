package com.study.PO.controllers;

import java.util.Comparator;
import java.util.List;

import com.study.PO.entities.kierunek.Opiekun;
import com.study.PO.entities.wydzial.Wydzial;
import com.study.PO.services.OpiekunService;
import com.study.PO.services.WydzialService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.services.KierunekService;

import org.springframework.ui.Model;


@Controller
@RequestMapping("/DzialIT")
public class DzialITController {
    private final KierunekService kierunekService;

    private final WydzialService wydzialService;

    private final OpiekunService opiekunService;

    @Autowired
    public DzialITController(KierunekService kierunekService, WydzialService wydzialService, OpiekunService opiekunService) {
        this.kierunekService = kierunekService;
        this.wydzialService = wydzialService;
        this.opiekunService = opiekunService;
    }

    @GetMapping("/")
    public String getMainPage() {
       return "DzialIT/start_page";
   }
   @GetMapping("/zarzadzanieOplatamiRekrutacyjnymi")
   public String getOplatyRekrutacyjnePage(Model model) {
       model.addAttribute("kierunki", kierunekService.getAllKierunek());
       return "DzialIT/PU_1_wszytkieKierunki_page";
   }

@GetMapping("/zmienOplate/{id}/nowaCena/{cena}")
public String getZmienOplate(Model model, @PathVariable long id,@PathVariable double cena) {

    if (cena < 0){
        return "DzialIT/PU1_blednaNowaOplata";
    }else{
        model.addAttribute("kierunek", kierunekService.getKierunek(id));
        model.addAttribute("oplata", cena);
    
        return "DzialIT/PU1_potwierdzenieNowejOplaty";
    }
    
}
@GetMapping("/nowa/{id}/cena/{cena}")
    public String ZmienOplate(Model model,@PathVariable long id,@PathVariable double cena ) {
        Kierunek kierunek  = kierunekService.getKierunek(id);
        kierunek.setCenaZaWniosek(cena);
        kierunekService.updateKierunek(kierunek);
        model.addAttribute("kierunki", kierunekService.getAllKierunek());

        return "DzialIT/PU_1_wszytkieKierunki_page";
   }

    @GetMapping("zarzadzanieWydzialami")
    public String getWydzialy(Model model) {
        List<Wydzial> wydzialy = wydzialService.getAllWydzial();
        model.addAttribute("wydzialy", wydzialy);
        return "DzialIT/showWydzialy";
    }

    @GetMapping("zarzadzanieWydzialami/{wydzialId}")
    public String getWydzial(Model model, @PathVariable long wydzialId) {
        Wydzial wydzial = wydzialService.getWydzial(wydzialId);
        model.addAttribute("wydzial", wydzial);
        return "DzialIT/showWydzial";
    }

    @GetMapping("zarzadzanieWydzialami/{wydzialId}/dodawanieKierunku")
    public String showFormularzDodawanieKierunku(Model model, @PathVariable long wydzialId, HttpSession session) {
        Kierunek kierunek = (Kierunek) session.getAttribute("kierunek");
        if (kierunek == null) {
            kierunek = new Kierunek();
        }
        List<Opiekun> opiekunowi = opiekunService.getAllOpiekuny();
        opiekunowi.sort(Comparator.comparing(Opiekun::getNazwisko));
        model.addAttribute("opiekunowi", opiekunowi);
        model.addAttribute("kierunek", kierunek);
        model.addAttribute("wydzialId", wydzialId);
        session.removeAttribute("kierunek");
        return "DzialIT/addKierunekForm";
    }

    @PostMapping("zarzadzanieWydzialami/{wydzialId}/dodawanieKierunku/potwierdzenie")
    public String potwierdzenieDodania(Model model, @PathVariable long wydzialId, HttpSession session, @Valid @ModelAttribute("kierunek") Kierunek kierunek, BindingResult bindingResult) {
        session.setAttribute("kierunek", kierunek);
        if (bindingResult.hasErrors()) {
            model.addAttribute("kierunek", kierunek);
            return "DzialIT/addKierunekForm";
        }
        else {
            List<Kierunek> kierunki = kierunekService.getAllKierunek();
            for (Kierunek kierunek1 : kierunki) {
                if (kierunek1.getNazwa().equals(kierunek.getNazwa())) {
                    model.addAttribute("wydzialId", wydzialId);
                    return "DzialIT/nazwaKierunkuIstnieje";
                }
            }
            model.addAttribute("kierunekNazwa", kierunek.getNazwa());
            model.addAttribute("wydzialNazwa", wydzialService.getWydzial(wydzialId).getNazwa());
            model.addAttribute("wydzialId", wydzialId);
        }
        return "DzialIT/potwiedzenieDodania";
    }
    @PostMapping("zarzadzanieWydzialami/{wydzialId}/dodawanieKierunku")
    public String dodawanieKierunku(@PathVariable long wydzialId, HttpSession session) {
        Kierunek kierunek = (Kierunek) session.getAttribute("kierunek");
        Opiekun savedOpiekun = opiekunService.addOpiekun(kierunek.getOpiekun());
        kierunek.setOpiekun(savedOpiekun);
        kierunek.setWydzial(wydzialService.getWydzial(wydzialId));
        kierunekService.addKierunek(kierunek);
        session.removeAttribute("kierunek");
        return "redirect:/DzialIT/zarzadzanieWydzialami";
    }
}