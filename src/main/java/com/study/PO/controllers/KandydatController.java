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

import com.study.PO.entities.kandydat.Kandydat;
import com.study.services.KandydatService;


@RestController
public class KandydatController {
    
    @Autowired
    private KandydatService kandydatService;

    @GetMapping("/kandydat")
    public List<Kandydat> getAllKandydats(){
        return kandydatService.getAllKandydats();
    }

	@GetMapping("/kandydat/{id}")
	public Kandydat getKandydat(@PathVariable("id") Long id){
        return kandydatService.getKandydat(id);
    }

    @PostMapping(value = "/kandydat",     consumes = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<?> saveKandydat(@RequestBody Kandydat kandydat) {
        try{
            kandydatService.addKandydat(kandydat);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Kandydat już instieje");
        }
	}

    @PutMapping(value = "/kandydat",     consumes = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<?> updateKandydat (@RequestBody Kandydat kandydat) {
        try{
            kandydatService.updateKandydat(kandydat);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error w aktualizowaniu kandydata");
        }
    }

    @DeleteMapping("/kandydat/{id}")
    public ResponseEntity<?> deleteKandydat(@PathVariable("id") Long id) {
        try{
            kandydatService.removeKandydatID(id);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error w usunięciu kandydata");
        }
    }
}
