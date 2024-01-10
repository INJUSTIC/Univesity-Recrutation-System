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

import com.study.PO.entities.wniosek.Wniosek;
import com.study.services.WniosekService;


@RestController
public class WniosekController {
    
    @Autowired
    private WniosekService wniosekService;

    @GetMapping("/wniosek")
    public List<Wniosek> getAllWnioseks(){
        return wniosekService.getAllWniosek();
    }

	@GetMapping("/wniosek/{id}")
	public Wniosek getWniosek(@PathVariable("id") Long id){
        return wniosekService.getWniosek(id);
    }

    @PostMapping(value = "/wniosek",     consumes = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<?> saveWniosek(@RequestBody Wniosek wniosek) {
        try{
            wniosekService.addWniosek(wniosek);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Wniosek już instieje");
        }
	}

    @PutMapping(value = "/wniosek",     consumes = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<?> updateWniosek (@RequestBody Wniosek wniosek) {
        try{
            wniosekService.updateWniosek(wniosek);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error w aktualizowaniu wniosku");
        }
    }

    @DeleteMapping("/wniosek/{id}")
    public ResponseEntity<?> deleteWniosek(@PathVariable("id") Long id) {
        try{
            wniosekService.removeWniosekID(id);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error w usunięciu wniosku");
        }
    }
}
