package com.study.PO.controllers;

import com.study.PO.entities.dokument.Dokument;
import com.study.PO.entities.kierunek.StopienStudiow;
import com.study.PO.entities.wniosek.DaneIdentWniosek;
import com.study.PO.entities.wniosek.StatusWniosku;
import com.study.PO.entities.wniosek.Wniosek;
import com.study.PO.services.WniosekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recruitmentDepartment")
public class DzialRekrutacjiController {
    private final WniosekService wniosekService;

    @Autowired
    public DzialRekrutacjiController(WniosekService wniosekService) {
        this.wniosekService = wniosekService;
    }

    @GetMapping("/main")
    public String getMainPage() {
       return "recruitmentDepartment/main";
   }

   @GetMapping("/recrutationPerforming/applications")
    public String getApplications(Model model) {
       List<Wniosek> wnioski = wniosekService.getAllWniosek();
       List<DaneIdentWniosek> wnioskiDane = getDaneIdentWniosek(wnioski);
       model.addAttribute("wnioski", wnioskiDane);
       return "recruitmentDepartment/applications/all";
   }

   private String getApplications(Model model, List<Wniosek> wnioski) {
       List<DaneIdentWniosek> wnioskiDane = getDaneIdentWniosek(wnioski);
       model.addAttribute("wnioski", wnioskiDane);
       return "recruitmentDepartment/applications/all";
   }
   @GetMapping("/recrutationPerforming/applications/filterByKierunek")
    public String filterByKierunek(Model model, @RequestParam String kierunek) {
        List<Wniosek> wnioski = wniosekService.getWnioskiByKierunek(kierunek);
        return getApplications(model, wnioski);
   }

    @GetMapping("/recrutationPerforming/applications/filterByStatus")
    public String filterByStatus(Model model, @RequestParam StatusWniosku statusWniosku) {
        List<Wniosek> wnioski = wniosekService.getWnioskiByStatus(statusWniosku);
        return getApplications(model, wnioski);
    }

    @GetMapping("/recrutationPerforming/applications/filterByStopien")
    public String filterByStopien(Model model, @RequestParam StopienStudiow stopienKierunku) {
        List<Wniosek> wnioski = wniosekService.getWnioskiByStopienKierunku(stopienKierunku);
        return getApplications(model, wnioski);
    }

    @GetMapping("/recrutationPerforming/applications/filterByWydzial")
    public String filterByWydzial(Model model, @RequestParam String wydzialName) {
        List<Wniosek> wnioski = wniosekService.getWnioskiByWydzial(wydzialName);
        return getApplications(model, wnioski);
    }

    @GetMapping("/recrutationPerforming/applications/{applicationId}")
    public String getWniosek(Model model, @PathVariable long applicationId) {
        Wniosek wniosek = wniosekService.getWniosek(applicationId);
        model.addAttribute("wniosek", wniosek);
        return "applications/showApplication";
    }

    @GetMapping("/recrutationPerforming/applications/{applicationId}/documents")
    public String getDocuments(Model model, @PathVariable long applicationId) {
        Wniosek wniosek = wniosekService.getWniosek(applicationId);
        List<Dokument> documents = wniosek.getDokumenty();
        model.addAttribute("dokumenty", documents);
        return "applications/showDocuments";
    }

    @GetMapping("/recrutationPerforming/applications/{applicationId}/documents")

   private List<DaneIdentWniosek> getDaneIdentWniosek(List<Wniosek> wnioski) {
       List<DaneIdentWniosek> identWnioski = new ArrayList<>();
       for (Wniosek wniosek : wnioski) {
           StatusWniosku statusWniosku = wniosek.getStatusWniosku();
           String imie = wniosek.getDanePersonalne().getImie();
           String nazwisko = wniosek.getDanePersonalne().getNazwisko();
           String nazwaKierunku = wniosek.getKierunek().getNazwa();
           StopienStudiow stopienKierunku = wniosek.getKierunek().getStopienStudiow();
           identWnioski.add(new DaneIdentWniosek(statusWniosku,imie,nazwisko,nazwaKierunku,stopienKierunku));
       }
       return identWnioski;
   }
}
