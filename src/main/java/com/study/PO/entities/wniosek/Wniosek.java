package com.study.PO.entities.wniosek;

import com.study.PO.entities.dokument.Dokument;
import com.study.PO.entities.kandydat.Kandydat;
import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.entities.wniosek.dane.DaneDodatkowe;
import com.study.PO.entities.wniosek.dane.DanePersonalne;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "wnioski")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wniosek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "status_wniosku")
    @NotNull(message = "status wniosku jest wymagany")
    private StatusWniosku statusWniosku;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dane_personalne")
    private DanePersonalne danePersonalne;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dane_dodatkowe")
    private DaneDodatkowe daneDodatkowe;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "kierunek_id")
    private Kierunek kierunek;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "kandydat_id")
    private Kandydat kandydat;

    @OneToMany(mappedBy = "wniosek",
            cascade = CascadeType.ALL)
    private List<Dokument> dokumenty;
}
