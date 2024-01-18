package com.study.PO.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.services.KierunekService;

import jakarta.validation.constraints.Null;

import org.springframework.ui.Model;


@Controller
@RequestMapping("/DzialIT")
public class DzialITController {
    private Kierunek aktualnyKierunek = null;

    @Autowired
    KierunekService kierunekService;

    @GetMapping("/")
    public String getMainPage() {
       return "DzialIT/main";
   }
   @GetMapping("/zarzadzanieOplatamiRekrutacyjnymi")
   public String getOplatyRekrutacyjnePage(Model model) {
    // List<Kierunek> listk = kierunekService.getAllKierunek();
    // System.out.println("lol");
    // for (int i = 0; i < listk.size(); i++){
    //     System.out.println(listk.get(i).getNazwa());
    // }
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
}