package com.study.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.PO.entities.kandydat.Kandydat;
import com.study.PO.repositories.KandydatRepository;



@Service
public class KandydatService {
    

    @Autowired
    private KandydatRepository repositoryKandydat;

    public List<Kandydat> getAllKandydats() {
        return repositoryKandydat.findAll();
    }

    public Kandydat getKandydat(Long id) {
        return repositoryKandydat.findById(id).orElse(null);
    }

    
    public Kandydat addKandydat(Kandydat dokument) {
        return repositoryKandydat.saveAndFlush(dokument);
    }
    

    public void updateKandydat(Kandydat d){
        repositoryKandydat.save(d);
    }

    public void removeKandydat(Kandydat d){
        repositoryKandydat.delete(d);
    }

    public void removeKandydatID(Long id){
        repositoryKandydat.deleteById(id);
    }
    
}
