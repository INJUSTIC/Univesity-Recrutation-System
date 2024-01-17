package com.study.PO.viewModels;

import java.util.List;

public class PU7wyborEgzaminowMaturalnychViewModel {
    private List<String> wybraneEgzaminyPodstawa;
    private List<String> wybraneEgzaminyRozszerzenie;
    private boolean egzaminRysunek;
    private boolean studiumTalentMatematyka;
    private boolean studiumTalentFizyka;

    public List<String> getWybraneEgzaminyPodstawa() {
        return wybraneEgzaminyPodstawa;
    }

    public void setWybraneEgzaminyPodstawa(List<String> wybraneEgzaminyPodstawa) {
        this.wybraneEgzaminyPodstawa = wybraneEgzaminyPodstawa;
    }

    public List<String> getWybraneEgzaminyRozszerzenie() {
        return wybraneEgzaminyRozszerzenie;
    }

    public void setWybraneEgzaminyRozszerzenie(List<String> wybraneEgzaminyRozszerzenie) {
        this.wybraneEgzaminyRozszerzenie = wybraneEgzaminyRozszerzenie;
    }

    public boolean isEgzaminRysunek() {
        return egzaminRysunek;
    }

    public void setEgzaminRysunek(boolean egzaminRysunek) {
        this.egzaminRysunek = egzaminRysunek;
    }

    public boolean isStudiumTalentMatematyka() {
        return studiumTalentMatematyka;
    }

    public void setStudiumTalentMatematyka(boolean studiumTalentMatematyka) {
        this.studiumTalentMatematyka = studiumTalentMatematyka;
    }

    public boolean isStudiumTalentFizyka() {
        return studiumTalentFizyka;
    }

    public void setStudiumTalentFizyka(boolean studiumTalentFizyka) {
        this.studiumTalentFizyka = studiumTalentFizyka;
    }
}
