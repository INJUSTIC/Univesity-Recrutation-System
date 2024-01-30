package com.study.PO.viewModels;

import com.study.PO.entities.kierunek.StopienStudiow;
import com.study.PO.entities.kierunek.wskaznik.Rodzaje_kryteriow;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
@NoArgsConstructor
public class PU7wynikiInzynierskieViewModel {
//    private Rodzaje_kryteriow kryterium;

    private String[] nazwyEgzaminowPodstawa;
    private String[] nazwyEgzaminowRozszerzenie;
    private boolean czyEgzaminRysunek;
    private boolean czyStudiumTalentMatematyka;
    private boolean czyStudiumTalentFizyka;

    private boolean czyEgzaminDodatkowy;

    private Map<String, Double> wynikiPodstawa;
    private Map<String, Double> wynikiRozszerzenie;
    private Set<String> nazwyEgzaminow;
    private double wynikEgzaminRysunek;
    private double wynikStudiumTalentMatematyka;
    private double wynikStudiumTalentFizyka;

    public void copyAttributes(PU7wynikiInzynierskieViewModel otherViewModel) {
        this.nazwyEgzaminowPodstawa = otherViewModel.nazwyEgzaminowPodstawa;
        this.nazwyEgzaminowRozszerzenie = otherViewModel.nazwyEgzaminowRozszerzenie;
        this.czyStudiumTalentMatematyka = otherViewModel.czyStudiumTalentMatematyka;
        this.czyStudiumTalentFizyka = otherViewModel.czyStudiumTalentFizyka;
        this.czyEgzaminRysunek = otherViewModel.czyEgzaminRysunek;
        this.initWyniki();
    }

    public void initWyniki() {
        wynikiPodstawa = new HashMap<>();
        nazwyEgzaminow = new HashSet<>();
        if (nazwyEgzaminowPodstawa != null) {
            for (String nazwaEP : nazwyEgzaminowPodstawa) {
                wynikiPodstawa.put(nazwaEP, 0.0);
                nazwyEgzaminow.add(nazwaEP);
            }
        } else
            nazwyEgzaminowPodstawa = new String[0];

        wynikiRozszerzenie = new HashMap<>();
        if (nazwyEgzaminowRozszerzenie != null) {
            for (String nazwaER : nazwyEgzaminowRozszerzenie) {
                wynikiRozszerzenie.put(nazwaER, 0.0);
                nazwyEgzaminow.add(nazwaER);
            }
        }
        else
            nazwyEgzaminowRozszerzenie = new String[0];
        czyEgzaminDodatkowy = czyEgzaminRysunek || czyStudiumTalentFizyka || czyStudiumTalentMatematyka;

//        wynikEgzaminRysunek = 0.0;
//        wynikStudiumTalentMatematyka = 0.0;
//        wynikStudiumTalentFizyka = 0.0;
    }
}
