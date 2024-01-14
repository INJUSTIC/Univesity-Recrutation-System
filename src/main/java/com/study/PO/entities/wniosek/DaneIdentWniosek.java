package com.study.PO.entities.wniosek;

import com.study.PO.entities.kierunek.StopienStudiow;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DaneIdentWniosek {
    private long id;
    private StatusWniosku statusWniosku;
    private String imie;
    private String nazwisko;
    private String nazwaKierunku;
    private StopienStudiow stopienStudiow;

}
