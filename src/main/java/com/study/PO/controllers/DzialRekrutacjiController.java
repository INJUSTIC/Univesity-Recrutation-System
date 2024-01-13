package com.study.PO.controllers;

import com.study.PO.entities.dokument.Dokument;
import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.entities.kierunek.StopienStudiow;
import com.study.PO.entities.wniosek.DaneIdentWniosek;
import com.study.PO.entities.wniosek.StatusWniosku;
import com.study.PO.entities.wniosek.Wniosek;
import com.study.PO.entities.wydzial.Wydzial;
import com.study.PO.services.DokumentService;
import com.study.PO.services.KierunekService;
import com.study.PO.services.WniosekService;
import com.study.PO.services.WydzialService;
import jakarta.validation.Valid;
import org.attoparser.dom.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recruitmentDepartment")
public class DzialRekrutacjiController {
    private final WniosekService wniosekService;
    private final DokumentService dokumentService;
    private final KierunekService kierunekService;

    private final WydzialService wydzialService;

    private final ResourceLoader resourceLoader;

    @Autowired
    public DzialRekrutacjiController(WniosekService wniosekService, DokumentService dokumentService, KierunekService kierunekService, WydzialService wydzialService, ResourceLoader resourceLoader) {
        this.wniosekService = wniosekService;
        this.dokumentService = dokumentService;
        this.resourceLoader = resourceLoader;
        this.kierunekService = kierunekService;
        this.wydzialService = wydzialService;
    }

    @GetMapping("/main")
    public String getMainPage() {
       return "recruitmentDepartment/main";
   }

   /*@GetMapping("/recrutationPerforming/applications")
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

    @GetMapping("/recrutationPerforming/applications/{applicationId}/documents/{documentId}")
    public String getDocument(Model model, @PathVariable long documentId) {
        Dokument document = dokumentService.getDokument(documentId);
        model.addAttribute("document", document);
        return "applications/showDocument";
    }

    @GetMapping("/recrutationPerforming/applications/{applicationId}/documents/{documentId}/download")
    public ResponseEntity<InputStreamResource> downloadDocument(@PathVariable long documentId) throws IOException {
        String documentName = dokumentService.getDokument(documentId).getNazwaDokumentu();
        ClassPathResource documentResource = new ClassPathResource(documentName);
        InputStream documentStream = documentResource.getInputStream();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + documentName);

        // Set the content type based on the document type
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(documentStream));
    }*/

    @GetMapping("/recrutationPerforming/applications")
    public List<DaneIdentWniosek> getApplications(@RequestParam(required = false) String kierunek, @RequestParam(required = false) StatusWniosku statusWniosku, @RequestParam(required = false) StopienStudiow stopienKierunku, @RequestParam(required = false) String wydzialNameOrCode) {
        List<Wniosek> wnioski = wniosekService.getAllWniosek();
        if (kierunek != null) {
            wnioski = wnioski.stream().filter(Wniosek -> Wniosek.getKierunek().getNazwa().equals(kierunek)).toList();
        }
        if (statusWniosku != null) {
            wnioski = wnioski.stream().filter(Wniosek -> Wniosek.getStatusWniosku() == statusWniosku).toList();
        }
        if (stopienKierunku != null) {
            wnioski = wnioski.stream().filter(Wniosek -> Wniosek.getKierunek().getStopienStudiow() == stopienKierunku).toList();
        }
        if (wydzialNameOrCode != null) {
            wnioski = wnioski.stream().filter(Wniosek -> Wniosek.getKierunek().getWydzial().getNazwa().equals(wydzialNameOrCode)).toList();
        }
        List<DaneIdentWniosek> wnioskiDane = getDaneIdentWniosek(wnioski);
        return wnioskiDane;
    }

    @GetMapping("/recrutationPerforming/applications/{applicationId}")
    public Wniosek getWniosek(@PathVariable long applicationId) {
        Wniosek wniosek = wniosekService.getWniosek(applicationId);
        return wniosek;
    }

    @GetMapping("/recrutationPerforming/applications/{applicationId}/documents")
    public List<Dokument> getDocuments(@PathVariable long applicationId) {
        Wniosek wniosek = wniosekService.getWniosek(applicationId);
        List<Dokument> documents = wniosek.getDokumenty();
        return documents;
    }

    @GetMapping("/recrutationPerforming/applications/{applicationId}/documents/{documentId}")
    public Dokument getDocument(@PathVariable long documentId) {
        Dokument document = dokumentService.getDokument(documentId);
        return document;
    }

    @GetMapping("/recrutationPerforming/applications/{applicationId}/documents/{documentId}/download")
    public ResponseEntity<InputStreamResource> downloadDocument(@PathVariable long documentId) throws IOException {
        String documentName = dokumentService.getDokument(documentId).getNazwaDokumentu();
        Resource documentResource = resourceLoader.getResource("classpath:documentsFiles/" + documentName);
        InputStream documentStream = documentResource.getInputStream();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + documentName);

        // Set the content type based on the document type
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(documentStream));
    }

    @GetMapping("facultyManagement")
    public String getAllFaculties(Model model) {
        List<Wydzial> wydzialy = wydzialService.getAllWydzial();
        model.addAttribute("wydzialy", wydzialy);
        return "recruitmentDepartment/wydzialy/showAll";
    }

    @GetMapping("facultyManagement/{facultyId}")
    public String getFaculty(Model model, @PathVariable long facultyId) {
        Wydzial wydzial = wydzialService.getWydzial(facultyId);
        model.addAttribute("wydzial", wydzial);
        return "recruitmentDepartment/wydzialy/showWydzial";
    }

    @GetMapping("facultyManagement/{facultyId}/addSpecialization")
    public String showAddSpecializationForm(Model model, @PathVariable long facultyId) {
        Kierunek kierunek = new Kierunek();
        model.addAttribute("kierunek", kierunek);
        model.addAttribute("wydzialId", facultyId);
        return "recruitmentDepartment/kierunki/addKierunekForm";
    }

    @PostMapping("facultyManagement/{facultyId}/addSpecialization")
    public String addKierunek(Model model, @Valid @ModelAttribute("kierunek") Kierunek kierunek, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("kierunek", kierunek);
            return "recruitmentDepartment/kierunki/addKierunekForm";
        }
        else {
            kierunekService.addKierunek(kierunek);
        }
        return "redirect:/facultyManagement";
    }

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
