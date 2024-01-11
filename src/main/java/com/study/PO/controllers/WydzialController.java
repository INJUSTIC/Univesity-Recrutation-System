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

import com.study.PO.entities.wydzial.Wydzial;
import com.study.PO.services.WydzialService;



@RestController
public class WydzialController {
    
    @Autowired
    private WydzialService wydzialService;

    @GetMapping("/wydzial")
    public List<Wydzial> getAllWydzial(){
        return wydzialService.getAllWydzial();
    }

	@GetMapping("/wydzial/{id}")
	public Wydzial getWydzial(@PathVariable("id") Long id){
        return wydzialService.getWydzial(id);
    }

    @PostMapping(value = "/wydzial",     consumes = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<?> saveWydzial(@RequestBody Wydzial wydzial) {
        try{
            wydzialService.addWydzial(wydzial);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Wydzial już instieje");
        }
	}

    @PutMapping(value = "/wydzial",     consumes = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<?> updateWniosek (@RequestBody Wydzial wydzial) {
        try{
            wydzialService.updateWydzial(wydzial);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error w aktualizowaniu wydzial");
        }
    }

    @DeleteMapping("/wydzial/{id}")
    public ResponseEntity<?> deleteWydzial(@PathVariable("id") Long id) {
        try{
            wydzialService.removeWydzialID(id);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error w usunięciu wydzial");
        }
    }
}
