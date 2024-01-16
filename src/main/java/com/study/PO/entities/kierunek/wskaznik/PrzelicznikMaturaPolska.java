package com.study.PO.entities.kierunek.wskaznik;

public class PrzelicznikMaturaPolska implements PrzelicznikKryterium{

    private MaturalnyPrzedmiotDodatkowy kryteria;

    public PrzelicznikMaturaPolska(MaturalnyPrzedmiotDodatkowy kryteria) {
        this.kryteria = kryteria;
    }

    public double przeliczKryterium(Wyniki wyniki) {
        WynikiMaturaPolska wynikiMP = (WynikiMaturaPolska) wyniki;

        double matematyka = Math.max(wynikiMP.getMatematykaPodstawa(), Math.max(wynikiMP.getMatematykaPodstawa() + 1.5 * wynikiMP.getMatematykaRozszerzenie(), 2.5 * wynikiMP.getMatematykaRozszerzenie()));

        double przedmiotDodatkowy = 0;
        double wynik;
        for (MaturalnePrzedmiotyDodatkowe przedmiotDod : MaturalnePrzedmiotyDodatkowe.values()) {
            switch (przedmiotDod) {
                case FIZYKA -> {
                    if (kryteria.isCzyFizyka()) {
                        wynik = Math.max(wynikiMP.getFizykaPodstawa(), Math.max(wynikiMP.getFizykaPodstawa() + 1.5 * wynikiMP.getFizykaRozszerzenie(), 2.5 * wynikiMP.getFizykaRozszerzenie()));
                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
                    }
                    break;
                }
                case CHEMIA -> {
                    if (kryteria.isCzyChemia()) {
                        wynik = Math.max(wynikiMP.getChemiaPodstawa(), Math.max(wynikiMP.getChemiaPodstawa() + 1.5 * wynikiMP.getChemiaRozszerzenie(), 2.5 * wynikiMP.getChemiaRozszerzenie()));
                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
                    }
                    break;
                }
                case BIOLOGIA -> {
                    if (kryteria.isCzyBiologia()) {
                        wynik = Math.max(wynikiMP.getBiologiaPodstawa(), Math.max(wynikiMP.getBiologiaPodstawa() + 1.5 * wynikiMP.getBiologiaRozszerzenie(), 2.5 * wynikiMP.getBiologiaRozszerzenie()));
                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
                    }
                    break;
                }
                case GEOGRAFIA -> {
                    if (kryteria.isCzyGeografia()) {
                        wynik = Math.max(wynikiMP.getGeografiaPodstawa(), Math.max(wynikiMP.getGeografiaPodstawa() + 1.5 * wynikiMP.getGeografiaRozszerzenie(), 2.5 * wynikiMP.getGeografiaRozszerzenie()));
                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
                    }
                    break;
                }
                case INFORMATYKA -> {
                    if (kryteria.isCzyInformatyka()) {
                        wynik = Math.max(wynikiMP.getInformatykaPodstawa(), Math.max(wynikiMP.getInformatykaPodstawa() + 1.5 * wynikiMP.getInformatykaRozszerzenie(), 2.5 * wynikiMP.getInformatykaRozszerzenie()));
                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
                    }
                    break;
                }
            }
        }

        double jezyk_obcy = Math.max(wynikiMP.getJezyk_obcyPodstawa(), Math.max(wynikiMP.getJezyk_obcyPodstawa() + 1.5 * wynikiMP.getJezyk_obcyRozszerzenie(), 2.5 * wynikiMP.getJezyk_obcyRozszerzenie()));
        double jezyk_polski = Math.max(wynikiMP.getPolskiPodstawa(), wynikiMP.getPolskiRozszerzenie());
        return (matematyka + przedmiotDodatkowy + (0.1 * jezyk_obcy) + (0.1 * jezyk_polski));
    }
}
