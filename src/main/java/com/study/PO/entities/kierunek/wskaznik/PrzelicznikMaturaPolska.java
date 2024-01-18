package com.study.PO.entities.kierunek.wskaznik;

import java.util.Map;

public class PrzelicznikMaturaPolska implements PrzelicznikKryterium{

    private MaturalnyPrzedmiotDodatkowy kryteria;

    public PrzelicznikMaturaPolska(MaturalnyPrzedmiotDodatkowy kryteria) {
        this.kryteria = kryteria;
    }

//    public double przeliczKryterium(Wyniki wyniki) {
//        WynikiMaturaPolska wynikiMP = (WynikiMaturaPolska) wyniki;
//
//        double matematyka = Math.max(wynikiMP.getMatematykaPodstawa(), Math.max(wynikiMP.getMatematykaPodstawa() + 1.5 * wynikiMP.getMatematykaRozszerzenie(), 2.5 * wynikiMP.getMatematykaRozszerzenie()));
//
//        double przedmiotDodatkowy = 0;
//        double wynik;
//        for (MaturalnePrzedmiotyDodatkowe przedmiotDod : MaturalnePrzedmiotyDodatkowe.values()) {
//            switch (przedmiotDod) {
//                case FIZYKA -> {
//                    if (kryteria.isCzyFizyka()) {
//                        wynik = Math.max(wynikiMP.getFizykaPodstawa(), Math.max(wynikiMP.getFizykaPodstawa() + 1.5 * wynikiMP.getFizykaRozszerzenie(), 2.5 * wynikiMP.getFizykaRozszerzenie()));
//                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
//                    }
//                    break;
//                }
//                case CHEMIA -> {
//                    if (kryteria.isCzyChemia()) {
//                        wynik = Math.max(wynikiMP.getChemiaPodstawa(), Math.max(wynikiMP.getChemiaPodstawa() + 1.5 * wynikiMP.getChemiaRozszerzenie(), 2.5 * wynikiMP.getChemiaRozszerzenie()));
//                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
//                    }
//                    break;
//                }
//                case BIOLOGIA -> {
//                    if (kryteria.isCzyBiologia()) {
//                        wynik = Math.max(wynikiMP.getBiologiaPodstawa(), Math.max(wynikiMP.getBiologiaPodstawa() + 1.5 * wynikiMP.getBiologiaRozszerzenie(), 2.5 * wynikiMP.getBiologiaRozszerzenie()));
//                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
//                    }
//                    break;
//                }
//                case GEOGRAFIA -> {
//                    if (kryteria.isCzyGeografia()) {
//                        wynik = Math.max(wynikiMP.getGeografiaPodstawa(), Math.max(wynikiMP.getGeografiaPodstawa() + 1.5 * wynikiMP.getGeografiaRozszerzenie(), 2.5 * wynikiMP.getGeografiaRozszerzenie()));
//                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
//                    }
//                    break;
//                }
//                case INFORMATYKA -> {
//                    if (kryteria.isCzyInformatyka()) {
//                        wynik = Math.max(wynikiMP.getInformatykaPodstawa(), Math.max(wynikiMP.getInformatykaPodstawa() + 1.5 * wynikiMP.getInformatykaRozszerzenie(), 2.5 * wynikiMP.getInformatykaRozszerzenie()));
//                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
//                    }
//                    break;
//                }
//            }
//        }
//
//        double jezyk_obcy = Math.max(wynikiMP.getJezyk_obcyPodstawa(), Math.max(wynikiMP.getJezyk_obcyPodstawa() + 1.5 * wynikiMP.getJezyk_obcyRozszerzenie(), 2.5 * wynikiMP.getJezyk_obcyRozszerzenie()));
//        double jezyk_polski = Math.max(wynikiMP.getPolskiPodstawa(), wynikiMP.getPolskiRozszerzenie());
//        return (matematyka + przedmiotDodatkowy + (0.1 * jezyk_obcy) + (0.1 * jezyk_polski));

    public double przeliczKryterium(Wyniki wyniki) {

        Map<String, Double> podstawa = wyniki.getWynikiPodstawa();
        Map<String, Double> rozszerzenie = wyniki.getWynikiRozszerzenie();

        double matematykaPodstawa = 0.0;
        double fizykaPodstawa = 0.0;
        double biologiaPodstawa = 0.0;
        double chemiaPodstawa = 0.0;
        double geografiaPodstawa = 0.0;
        double informatykaPodstawa = 0.0;
        double polskiPodstawa = 0.0;
        double jezyk_obcyPodstawa = 0.0;
        double matematykaRozszerzenie = 0.0;
        double fizykaRozszerzenie = 0.0;
        double biologiaRozszerzenie = 0.0;
        double chemiaRozszerzenie = 0.0;
        double geografiaRozszerzenie = 0.0;
        double informatykaRozszerzenie = 0.0;
        double polskiRozszerzenie = 0.0;
        double jezyk_obcyRozszerzenie = 0.0;

        for (String key : podstawa.keySet()) {
            if (key.equalsIgnoreCase("matematyka")) {
                matematykaPodstawa = podstawa.get(key);
            }
            if (key.equalsIgnoreCase("fizyka")) {
                fizykaPodstawa = podstawa.get(key);
            }
            if (key.equalsIgnoreCase("biologia")) {
                biologiaPodstawa = podstawa.get(key);
            }
            if (key.equalsIgnoreCase("chemia")) {
                chemiaPodstawa = podstawa.get(key);
            }
            if (key.equalsIgnoreCase("geografia")) {
                geografiaPodstawa = podstawa.get(key);
            }
            if (key.equalsIgnoreCase("informatyka")) {
                informatykaPodstawa = podstawa.get(key);
            }
            if (key.equalsIgnoreCase("język polski")) {
                polskiPodstawa = podstawa.get(key);
            }
            if (key.equalsIgnoreCase("język obcy")) {
                jezyk_obcyPodstawa = podstawa.get(key);
            }
        }

        for (String key : rozszerzenie.keySet()) {
            if (key.equalsIgnoreCase("matematyka")) {
                matematykaRozszerzenie = rozszerzenie.get(key);
            }
            if (key.equalsIgnoreCase("fizyka")) {
                fizykaRozszerzenie = rozszerzenie.get(key);
            }
            if (key.equalsIgnoreCase("biologia")) {
                biologiaRozszerzenie = rozszerzenie.get(key);
            }
            if (key.equalsIgnoreCase("chemia")) {
                chemiaRozszerzenie = rozszerzenie.get(key);
            }
            if (key.equalsIgnoreCase("geografia")) {
                geografiaRozszerzenie = rozszerzenie.get(key);
            }
            if (key.equalsIgnoreCase("informatyka")) {
                informatykaRozszerzenie = rozszerzenie.get(key);
            }
            if (key.equalsIgnoreCase("język polski")) {
                polskiRozszerzenie = rozszerzenie.get(key);
            }
            if (key.equalsIgnoreCase("język obcy")) {
                jezyk_obcyRozszerzenie = rozszerzenie.get(key);
            }
        }
        double matematyka = Math.max(matematykaPodstawa, Math.max(matematykaPodstawa + 1.5 * matematykaRozszerzenie, 2.5 * matematykaRozszerzenie));

        double przedmiotDodatkowy = 0;
        double wynik;
        for (MaturalnePrzedmiotyDodatkowe przedmiotDod : MaturalnePrzedmiotyDodatkowe.values()) {
            switch (przedmiotDod) {
                case FIZYKA -> {
                    if (kryteria.isCzyFizyka()) {
                        wynik = Math.max(fizykaPodstawa, Math.max(fizykaPodstawa + 1.5 * fizykaRozszerzenie, 2.5 * fizykaRozszerzenie));
                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
                    }
                    break;
                }
                case CHEMIA -> {
                    if (kryteria.isCzyChemia()) {
                        wynik = Math.max(chemiaPodstawa, Math.max(chemiaPodstawa + 1.5 * chemiaRozszerzenie, 2.5 * chemiaRozszerzenie));
                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
                    }
                    break;
                }
                case BIOLOGIA -> {
                    if (kryteria.isCzyBiologia()) {
                        wynik = Math.max(biologiaPodstawa, Math.max(biologiaPodstawa + 1.5 * biologiaRozszerzenie, 2.5 * biologiaRozszerzenie));
                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
                    }
                    break;
                }
                case GEOGRAFIA -> {
                    if (kryteria.isCzyGeografia()) {
                        wynik = Math.max(geografiaPodstawa, Math.max(geografiaPodstawa + 1.5 * geografiaRozszerzenie, 2.5 * geografiaRozszerzenie));
                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
                    }
                    break;
                }
                case INFORMATYKA -> {
                    if (kryteria.isCzyInformatyka()) {
                        wynik = Math.max(informatykaPodstawa, Math.max(informatykaPodstawa + 1.5 * informatykaRozszerzenie, 2.5 * informatykaRozszerzenie));
                        przedmiotDodatkowy = Math.max(przedmiotDodatkowy, wynik);
                    }
                    break;
                }
            }
        }

        double jezyk_obcy = Math.max(jezyk_obcyPodstawa, Math.max(jezyk_obcyPodstawa + 1.5 * jezyk_obcyRozszerzenie, 2.5 * jezyk_obcyRozszerzenie));
        double jezyk_polski = Math.max(polskiPodstawa, polskiRozszerzenie);
        return (matematyka + przedmiotDodatkowy + (0.1 * jezyk_obcy) + (0.1 * jezyk_polski));
    }
}
