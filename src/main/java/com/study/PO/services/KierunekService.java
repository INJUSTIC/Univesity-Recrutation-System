package com.study.PO.services;

import java.util.List;

import com.study.PO.entities.kierunek.wskaznik.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.repositories.KierunekRepository;



@Service
public class KierunekService {
    

    @Autowired
    private KierunekRepository repositoryKierunek;

    public List<Kierunek> getAllKierunek() {
        return repositoryKierunek.findAll();
    }

    public Kierunek getKierunek(Long id) {
        return repositoryKierunek.findById(id).orElse(null);
    }

    
    public Kierunek addKierunek(Kierunek kierunek) {
        return repositoryKierunek.saveAndFlush(kierunek);
    }
    

    public void updateKierunek(Kierunek k){
        repositoryKierunek.save(k);
    }

    public void removeKierunek(Kierunek k){
        repositoryKierunek.delete(k);
    }

    public void removeKierunekID(Long id){
        repositoryKierunek.deleteById(id);
    }

    public PrzelicznikKryterium getPrzelicznikOfKryterium(Long id, Rodzaje_kryteriow rodzaj_kryterium){
        Kierunek kierunek = repositoryKierunek.findById(id).orElse(null);

        if (kierunek != null) {
            List<KryteriumWstepne> kryteriaWstepneList = kierunek.getKryteria();

            for (KryteriumWstepne kryteriumWstepne : kryteriaWstepneList) {
                Kryterium kryterium = kryteriumWstepne.getKryterium();

                if (kryterium.getNazwa() == rodzaj_kryterium.name())
                    return PrzelicznikFactory.getPrzelicznik(kryterium);
            }
        }
        return null;
    }
    
}
