package com.study.PO.services;

import java.util.List;

import com.study.PO.entities.kierunek.StopienStudiow;
import com.study.PO.entities.wniosek.StatusWniosku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.PO.entities.wniosek.Wniosek;
import com.study.PO.repositories.WniosekRepository;



@Service
public class WniosekService {
    

    @Autowired
    private WniosekRepository repositoryWniosek;

    public List<Wniosek> getAllWniosek() {
        return repositoryWniosek.findAll();
    }

    public Wniosek getWniosek(Long id) {
        return repositoryWniosek.findById(id).orElse(null);
    }

    
    public Wniosek addWniosek(Wniosek w) {
        return repositoryWniosek.saveAndFlush(w);
    }
    

    public void updateWniosek(Wniosek w){
        repositoryWniosek.save(w);
    }

    public void removeWniosek(Wniosek w){
        repositoryWniosek.delete(w);
    }

    public void removeWniosekID(Long id){
        repositoryWniosek.deleteById(id);
    }

    public List<Wniosek> getWnioskiByKierunek(String nazwaKierunku) {
        List<Wniosek> wnioski = getAllWniosek();
        return wnioski.stream().filter(Wniosek -> Wniosek.getKierunek().getNazwa().equals(nazwaKierunku)).toList();
    }

    public List<Wniosek> getWnioskiByStatus(StatusWniosku status) {
        List<Wniosek> wnioski = getAllWniosek();
        return wnioski.stream().filter(Wniosek -> Wniosek.getStatusWniosku() == status).toList();
    }

    public List<Wniosek> getWnioskiByStopienKierunku(StopienStudiow stopien) {
        List<Wniosek> wnioski = getAllWniosek();
        return wnioski.stream().filter(Wniosek -> Wniosek.getKierunek().getStopienStudiow() == stopien).toList();
    }

    public List<Wniosek> getWnioskiByWydzial(String wydzial) {
        List<Wniosek> wnioski = getAllWniosek();
        return wnioski.stream().filter(Wniosek -> Wniosek.getKierunek().getWydzial().getNazwa().equals(wydzial)).toList();
    }
    
}
