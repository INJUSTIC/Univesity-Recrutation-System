package com.study.PO.entities.kierunek.wskaznik;

public class PrzelicznikFactory {

    public static PrzelicznikKryterium getPrzelicznik(Kryterium kryterium) {
        switch(kryterium.getNazwa()) {
            case "MATURA_POLSKA" -> {
                return new PrzelicznikMaturaPolska((MaturalnyPrzedmiotDodatkowy) kryterium);
            }
            case "LEKARSKI" -> {
                return new PrzelicznikLekarski((KryteriumLekarski) kryterium);
            }
        }
        return null;
    }
}
