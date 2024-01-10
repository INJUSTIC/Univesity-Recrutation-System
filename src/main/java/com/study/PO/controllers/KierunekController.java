package com.study.PO.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.PO.entities.kierunek.Kierunek;
import com.study.services.KierunekService;


@RestController
public class KierunekController {
    
    @Autowired
    private KierunekService kierunekService;

    @GetMapping("/kierunek")
    public List<Kierunek> getAllKierunek(){
        return kierunekService.getAllKierunek();
    }

	@GetMapping("/kierunek/{id}")
	public Kierunek getKierunek(@PathVariable("id") Long id){
        return kierunekService.getKierunek(id);
    }

    @PostMapping(value = "/kierunek",     consumes = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<?> saveKierunek(@RequestBody Kierunek kierunek) {
        try{
            kierunekService.addKierunek(kierunek);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Kierunek już instieje");
        }
	}

    @PutMapping(value = "/kierunek",     consumes = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<?> updateKierunek (@RequestBody Kierunek kierunek) {
        try{
            kierunekService.updateKierunek(kierunek);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error w aktualizowaniu kierunku");
        }
    }

    @DeleteMapping("/kierunek/{id}")
    public ResponseEntity<?> deleteKierunek(@PathVariable("id") Long id) {
        try{
            kierunekService.removeKierunekID(id);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error w usunięciu kierunku");
        }
    }
}
