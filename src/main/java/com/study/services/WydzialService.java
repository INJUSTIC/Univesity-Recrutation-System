package com.study.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.PO.entities.wydzial.Wydzial;
import com.study.PO.repositories.WydzialRepository;




@Service
public class WydzialService {
    

    @Autowired
    private WydzialRepository repositoryWydzial;

    public List<Wydzial> getAllWydzial() {
        return repositoryWydzial.findAll();
    }

    public Wydzial getWydzial(Long id) {
        return repositoryWydzial.findById(id).orElse(null);
    }

    
    public Wydzial addWydzial(Wydzial w) {
        return repositoryWydzial.saveAndFlush(w);
    }
    

    public void updateWydzial(Wydzial w){
        repositoryWydzial.save(w);
    }

    public void removeWydzial(Wydzial w){
        repositoryWydzial.delete(w);
    }

    public void removeWydzialID(Long id){
        repositoryWydzial.deleteById(id);
    }
    
}
