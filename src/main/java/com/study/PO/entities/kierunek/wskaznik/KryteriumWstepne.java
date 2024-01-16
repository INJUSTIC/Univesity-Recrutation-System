package com.study.PO.entities.kierunek.wskaznik;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.PO.entities.dokument.TypDokumentu;
import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.entities.wniosek.Wniosek;
import com.study.PO.entities.wydzial.Wydzial;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "kryteria_wstepne")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KryteriumWstepne {
//    @EmbeddedId
//    private KryteriumWstepneId id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "kierunek_id")
    private Kierunek kierunek;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "kryterium_id")
    private Kryterium kryterium;
}