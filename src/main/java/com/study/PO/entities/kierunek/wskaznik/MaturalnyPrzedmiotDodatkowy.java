package com.study.PO.entities.kierunek.wskaznik;

import com.study.PO.entities.kierunek.Kierunek;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "maturalne_przedmioty_dodatkowe")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaturalnyPrzedmiotDodatkowy extends Kryterium{

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
