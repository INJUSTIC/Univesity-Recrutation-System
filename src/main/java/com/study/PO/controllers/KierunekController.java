package com.study.PO.controllers;

import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.repositories.KierunekRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:4200")
public class KierunekController {
    private final KierunekRepository kierunekRepository;

    public KierunekController(KierunekRepository kierunekRepository) {
        this.kierunekRepository = kierunekRepository;
    }

    @GetMapping("/kierunki")
    public List<Kierunek> getKierunki() {
        return (List<Kierunek>) kierunekRepository.findAll();
    }

    @PostMapping("/kierunki")
    void addKierunek(@RequestBody Kierunek kierunek) {
        kierunekRepository.save(kierunek);
    }
}
