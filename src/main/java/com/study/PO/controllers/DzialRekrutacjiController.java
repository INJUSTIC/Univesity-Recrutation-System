package com.study.PO.controllers;

import com.study.PO.entities.dokument.Dokument;
import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.entities.kierunek.StopienStudiow;
import com.study.PO.entities.wniosek.DaneIdentWniosek;
import com.study.PO.entities.wniosek.StatusWniosku;
import com.study.PO.entities.wniosek.Wniosek;
import com.study.PO.entities.wniosek.dane.DaneDodatkoweDok;
import com.study.PO.entities.wniosek.dane.DaneDodatkoweInz;
import com.study.PO.entities.wniosek.dane.DaneDodatkoweMag;
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

@Controller
@RequestMapping("/dzialRekrutacji")
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

    @GetMapping("/")
    public String getMainPage() {
       return "dzialRekrutacji/main";
   }

    @GetMapping("/przeprowadzenieRekrutacji")
    public String getPrzeprowadzenieRekrutacjiStrona() {
        return "dzialRekrutacji/przeprowadzenieRekrutacji";
    }

    @GetMapping("/przeprowadzenieRekrutacji/wnioski")
    public String getWnioski(Model model, @RequestParam(required = false) String kierunek, @RequestParam(required = false) StatusWniosku statusWniosku, @RequestParam(required = false) StopienStudiow stopienKierunku, @RequestParam(required = false) String wydzialNameOrCode) {
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
        model.addAttribute("wnioskiDane", wnioskiDane);
        return "dzialRekrutacji/showWnioski";
    }

    @GetMapping("/przeprowadzenieRekrutacji/wnioski/{wniosekId}")
    public String getWniosek(Model model, @PathVariable long wniosekId) {
        Wniosek wniosek = wniosekService.getWniosek(wniosekId);
        model.addAttribute("wniosek", wniosek);
        if (wniosek.getDaneDodatkowe() instanceof DaneDodatkoweDok) {
            model.addAttribute("isDok", true);
        }
        else if (wniosek.getDaneDodatkowe() instanceof DaneDodatkoweInz) {
            model.addAttribute("isInz", true);
        }
        else if (wniosek.getDaneDodatkowe() instanceof DaneDodatkoweMag) {
            model.addAttribute("isMag", true);
        }
        return "dzialRekrutacji/showWniosek";
    }

    @GetMapping("/przeprowadzenieRekrutacji/wnioski/{wniosekId}/dokumenty")
    public String getDokumenty(Model model, @PathVariable long wniosekId) {
        Wniosek wniosek = wniosekService.getWniosek(wniosekId);
        List<Dokument> documents = wniosek.getDokumenty();
        model.addAttribute("dokumentyWniosku", documents);
        return "dzialRekrutacji/showDokumenty";
    }

    @GetMapping("/przeprowadzenieRekrutacji/wnioski/{wniosekId}/dokumenty/{dokumentId}")
    public String getDokument(Model model, @PathVariable long dokumentId) {
        Dokument document = dokumentService.getDokument(dokumentId);
        model.addAttribute("dokument", document);
        return "dzialRekrutacji/showDokument";
    }

    @GetMapping("/przeprowadzenieRekrutacji/wnioski/{wniosekId}/dokumenty/{dokumentId}/pobierz")
    public ResponseEntity<InputStreamResource> pobierzDokument(@PathVariable long dokumentId) throws IOException {
        String documentName = dokumentService.getDokument(dokumentId).getNazwaDokumentu();
        Resource documentResource = resourceLoader.getResource("classpath:plikiDokumentow/" + documentName);
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

    @GetMapping("zarzadzanieWydzialami")
    public String getWydzialy(Model model) {
        List<Wydzial> wydzialy = wydzialService.getAllWydzial();
        model.addAttribute("wydzialy", wydzialy);
        return "dzialRekrutacji/showAll";
    }

    @GetMapping("zarzadzanieWydzialami/{wydzialId}")
    public String getWydzial(Model model, @PathVariable long wydzialId) {
        Wydzial wydzial = wydzialService.getWydzial(wydzialId);
        model.addAttribute("wydzial", wydzial);
        return "dzialRekrutacji/showWydzial";
    }

    @GetMapping("zarzadzanieWydzialami/{wydzialId}/dodawanieKierunku")
    public String showFormularzDodawanieKierunku(Model model, @PathVariable long wydzialId) {
        Kierunek kierunek = new Kierunek();
        model.addAttribute("kierunek", kierunek);
        model.addAttribute("wydzialId", wydzialId);
        return "dzialRekrutacji/addKierunekForm";
    }

    @PostMapping("zarzadzanieWydzialami/{wydzialId}/dodawanieKierunku")
    public String dodawanieKierunku(Model model, @Valid @ModelAttribute("kierunek") Kierunek kierunek, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("kierunek", kierunek);
            return "dzialRekrutacji/addKierunekForm";
        }
        else {
            kierunekService.addKierunek(kierunek);
        }
        return "redirect:/zarzadzanieWydzialami";
    }

   private List<DaneIdentWniosek> getDaneIdentWniosek(List<Wniosek> wnioski) {
       List<DaneIdentWniosek> identWnioski = new ArrayList<>();
       for (Wniosek wniosek : wnioski) {
           long id = wniosek.getId();
           StatusWniosku statusWniosku = wniosek.getStatusWniosku();
           String imie = wniosek.getDanePersonalne().getImie();
           String nazwisko = wniosek.getDanePersonalne().getNazwisko();
           String nazwaKierunku = wniosek.getKierunek().getNazwa();
           StopienStudiow stopienKierunku = wniosek.getKierunek().getStopienStudiow();
           identWnioski.add(new DaneIdentWniosek(id, statusWniosku,imie,nazwisko,nazwaKierunku,stopienKierunku));
       }
       return identWnioski;
   }
}
