package com.study.PO.viewModels;

import com.study.PO.entities.kierunek.wskaznik.Rodzaje_kryteriow;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
@NoArgsConstructor
public class PU7wynikiInzynierskieViewModel {
    private Rodzaje_kryteriow kryterium;

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

    public void initWyniki() {
        wynikiPodstawa = new HashMap<>();
        nazwyEgzaminow = new HashSet<>();
        for (String nazwaEP : nazwyEgzaminowPodstawa) {
            wynikiPodstawa.put(nazwaEP, 0.0);
            nazwyEgzaminow.add(nazwaEP);
        }

        wynikiRozszerzenie = new HashMap<>();
        for (String nazwaER : nazwyEgzaminowRozszerzenie) {
            wynikiRozszerzenie.put(nazwaER, 0.0);
            nazwyEgzaminow.add(nazwaER);
        }
        czyEgzaminDodatkowy = czyEgzaminRysunek || czyStudiumTalentFizyka || czyStudiumTalentMatematyka;

//        wynikEgzaminRysunek = 0.0;
//        wynikStudiumTalentMatematyka = 0.0;
//        wynikStudiumTalentFizyka = 0.0;
    }
}
