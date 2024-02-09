package com.study.PO.entities.kierunek.wskaznik;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class Wyniki {
    private Map<String, Double> wynikiPodstawa;
    private Map<String, Double> wynikiRozszerzenie;

    public Wyniki(Map<String, Double> wynikiPodstawa, Map<String, Double> wynikiRozszerzenie) {
        this.wynikiPodstawa = wynikiPodstawa;
        this.wynikiRozszerzenie = wynikiRozszerzenie;
    }
}
