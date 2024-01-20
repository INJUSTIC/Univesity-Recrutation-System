package com.study.PO.services;

import com.study.PO.entities.kierunek.Opiekun;
import com.study.PO.entities.wniosek.Wniosek;
import com.study.PO.repositories.OpiekunRepository;
import com.study.PO.repositories.WniosekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OpiekunService {
    @Autowired
    private OpiekunRepository opiekunRepository;

    public List<Opiekun> getAllOpiekuny() {
        return opiekunRepository.findAll();
    }

    public List<Opiekun> getAllAvailableOpiekuny() {
        return opiekunRepository.findAll().stream().filter(opiekun -> opiekun.getKierunek() == null).toList();
    }

    public Opiekun getOpiekun(Long id) {
        return opiekunRepository.findById(id).orElse(null);
    }


    public Opiekun addOpiekun(Opiekun w) {
        return opiekunRepository.saveAndFlush(w);
    }


    public void updateOpiekun(Opiekun w){
        opiekunRepository.save(w);
    }

    public void removeOpiekun(Opiekun w){
        opiekunRepository.delete(w);
    }

    public void removeOpiekunID(Long id){
        opiekunRepository.deleteById(id);
    }
}
