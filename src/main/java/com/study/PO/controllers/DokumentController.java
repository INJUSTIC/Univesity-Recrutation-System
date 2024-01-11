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

import com.study.PO.entities.dokument.Dokument;
import com.study.PO.services.DokumentService;


@RestController
public class DokumentController {
    
    @Autowired
    private DokumentService dokumentService;

    @GetMapping("/dokument")
    public List<Dokument> getAllDokumnty(){
        return dokumentService.getAllDokuments();
    }

	@GetMapping("/dokument/{id}")
	public Dokument getDokument(@PathVariable("id") Long id){
        return dokumentService.getDokument(id);
    }

    @PostMapping(value = "/dokument",     consumes = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<?> saveDokument(@RequestBody Dokument dokument) {
        try{
            dokumentService.addDokument(dokument);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Dokument już instieje");
        }
	}

    @PutMapping(value = "/dokument",     consumes = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<?> updateDokument (@RequestBody Dokument dokument) {
        try{
            dokumentService.updateDokument(dokument);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error w aktualizowaniu dokumentu");
        }
    }

    @DeleteMapping("/dokument/{id}")
    public ResponseEntity<?> deleteDokumnet(@PathVariable("id") Long id) {
        try{
            dokumentService.removeDokumentID(id);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error w usunięciu dokumentu");
        }
    }
}
