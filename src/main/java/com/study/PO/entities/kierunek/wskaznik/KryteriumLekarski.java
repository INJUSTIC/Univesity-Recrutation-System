package com.study.PO.entities.kierunek.wskaznik;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "kryteria_lekarski")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KryteriumLekarski extends Kryterium{

    @Column(name = "mnoznik_biologia")
    @NotBlank(message = "Okreslenie mnoznika dla biologii jest wymagane")
    private double mnoznikBiologia;

    @Column (name = "czy_fizyka")
    @NotBlank(message = "Okreslenie opcjonalnosci fizyki jest wymagane")
    private boolean czyFizyka;

    @Column (name = "czy_biologia")
    @NotBlank(message = "Okreslenie opcjonalnosci biologii jest wymagane")
    private boolean czyBiologia;

    @Column (name = "czy_chemia")
    @NotBlank(message = "Okreslenie opcjonalnosci chemii jest wymagane")
    private boolean czyChemia;

    @Column (name = "czy_geografia")
    @NotBlank(message = "Okreslenie opcjonalnosci geografii jest wymagane")
    private boolean czyGeografia;

    @Column (name = "czy_informatyka")
    @NotBlank(message = "Okreslenie opcjonalnosci informatyki jest wymagane")
    private boolean czyInformatyka;

}
