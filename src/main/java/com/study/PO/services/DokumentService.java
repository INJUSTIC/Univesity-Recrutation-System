package com.study.PO.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.PO.entities.dokument.Dokument;
import com.study.PO.repositories.DokumentRepository;



@Service
public class DokumentService {
    

    @Autowired
    private DokumentRepository repositoryDokument;

    public List<Dokument> getAllDokuments() {
        return repositoryDokument.findAll();
    }

    public Dokument getDokument(Long id) {
        return repositoryDokument.findById(id).orElse(null);
    }

    
    public Dokument addDokument(Dokument dokument) {
        return repositoryDokument.saveAndFlush(dokument);
    }
    

    public void updateDokument(Dokument d){
        repositoryDokument.save(d);
    }

    public void removeDokument(Dokument d){
        repositoryDokument.delete(d);
    }

    public void removeDokumentID(Long id){
        repositoryDokument.deleteById(id);
    }
    
}
