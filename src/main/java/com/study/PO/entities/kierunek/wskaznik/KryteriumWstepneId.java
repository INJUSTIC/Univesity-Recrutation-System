package com.study.PO.entities.kierunek.wskaznik;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.PO.entities.kierunek.Kierunek;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

//@Embeddable
//public class KryteriumWstepneId implements Serializable {
//
//    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinColumn(name = "kierunek_id")
//    private Kierunek kierunek;
//
//    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinColumn(name = "kryterium_id")
//    private Kryterium kryterium;
//
//    public Kierunek getKierunek() {
//        return kierunek;
//    }
//
//    public void setKierunek(Kierunek kierunek) {
//        this.kierunek = kierunek;
//    }
//
//    public Kryterium getKryterium() {
//        return kryterium;
//    }
//
//    public void setKryterium(Kryterium kryterium) {
//        this.kryterium = kryterium;
//    }
//
//}
