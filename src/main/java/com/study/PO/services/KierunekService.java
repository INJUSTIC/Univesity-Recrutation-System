package com.study.PO.services;

import java.util.List;

import com.study.PO.entities.kierunek.StopienStudiow;
import com.study.PO.entities.kierunek.wskaznik.*;
import com.study.PO.repositories.KryteriumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.repositories.KierunekRepository;



@Service
public class KierunekService {
    

    @Autowired
    private KierunekRepository repositoryKierunek;

    @Autowired
    private KryteriumRepository repositoryKryterium;

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

    public List<String> getRodzajeKryteriowStopienStudiow(StopienStudiow stopienStudiow){
        List<String> rodzajeKryteriow = repositoryKierunek.findDistinctRodzajeKryteriowByStopienStudiow(stopienStudiow.name());

        return rodzajeKryteriow;
    }

    public Kryterium getKryteriumOnNazwaKierunkuRodzajKryterium(String nazwaKierunku, Rodzaje_kryteriow rodzajKryt){
        switch (rodzajKryt) {
            case MATURA_POLSKA -> {
//                KierunekRepository.MPDvalues zapytanie = repositoryKierunek.findMPDbyNazwaKierunku(nazwaKierunku);
//                boolean czyFizyka = zapytanie.getCzyFizyka() != null ? zapytanie.getCzyFizyka() : false;
//                boolean czyBiologia = zapytanie.getCzyBiologia() != null ? zapytanie.getCzyBiologia() : false;
//                boolean czyChemia = zapytanie.getCzyChemia() != null ? zapytanie.getCzyChemia() : false;
//                boolean czyGeografia = zapytanie.getCzyGeografia() != null ? zapytanie.getCzyGeografia() : false;
//                boolean czyInformatyka = zapytanie.getCzyInformatyka() != null ? zapytanie.getCzyInformatyka() : false;
//                MaturalnyPrzedmiotDodatkowy mpd = new MaturalnyPrzedmiotDodatkowy(czyFizyka, czyBiologia, czyChemia, czyGeografia, czyInformatyka);
//                return mpd;
                Long mpdId = repositoryKierunek.findMPDbyNazwaKierunku(nazwaKierunku);
                return repositoryKryterium.findById(mpdId).isEmpty() ? null : (MaturalnyPrzedmiotDodatkowy) repositoryKryterium.findById(mpdId).get();
            }
        }
        return null;
    }
    
}
