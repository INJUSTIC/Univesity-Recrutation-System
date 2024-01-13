//TODO przechowywać historyczne dane kierunku w osobnej klasie?
//TODO zrobić custom getters dla pól pochodnych (obliczanych)

package com.study.PO.entities.kierunek;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.PO.entities.kandydat.Kandydat;
import com.study.PO.entities.wydzial.Wydzial;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "kierunki")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Kierunek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "Nazwa")
    @NotBlank(message = "Nazwa jest wymagana")
    @Size(max = 100, message = "Nazwa nie może przekraczać 100 znaków")
    private String nazwa;

    @Valid
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "opiekun_id")
    private Opiekun opiekun;

    @Column (name = "plan_studiow")
    @NotBlank
    @Size(max = 2048, message = "Plan studiów jest wymagany")
    private String planStudiow;

    @ElementCollection
    @CollectionTable(name = "progi_kierunku", joinColumns = @JoinColumn(name = "kierunek_id"))
    @Column(name = "prog")
    private List<Integer> przeszProgi;

    @Column (name = "prog_punktowy")
    @Min(value = 0, message = "Próg punktowy musi być liczbą dodatnią")
    private int progPunktowy;

    @Column (name = "progn_liczba_miejsc")
    @Min(value = 0, message = "Prognozowana liczba miejsc musi być liczbą naturalną")
    private int prognLiczbaMiejsc;

    @Column (name = "liczba_os_na_miejsce")
    @Min(value = 0, message = "Liczba osób na miejsce musi być liczbą dodatnia")
    private double liczbaOsNaMiejsce;

    @Column(name = "cena_za_wniosek")
    @Min(value = 0, message = "Cena musi być liczbą nieujemną")
    private double cenaZaWniosek;

    @ElementCollection
    @CollectionTable(name = "liczba_kandyd_w_poprz_lat", joinColumns = @JoinColumn(name = "kierunek_id"))
    @Column(name = "liczba")
    private List<Integer> liczbaKandydWPoprzLat;

    @Column (name = "liczba_chetnych")
    @Min(value = 0, message = "Liczba chętnych musi być liczbą naturalną")
    private int liczbaChetnych;

    @Enumerated(EnumType.STRING)
    @Column(name = "stopien_studiow")
    @NotNull(message = "stopień studiów jest wymagany")
    private StopienStudiow stopienStudiow;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "wydzial_id")
    private Wydzial wydzial;

//    public Kierunek(String nazwa, Opiekun opiekun, String planStudiow, List<Integer> przeszProgi, int progPunktowy, int prognLiczbaMiejsc, int liczbaOsNaMiejsce, double cenaZaWniosek, List<Integer> liczbaKandydWPoprzLat, int liczbaChetnych, StopienStudiow stopienStudiow, Wydzial wydzial) {
//        this.nazwa = nazwa;
//        this.opiekun = opiekun;
//        this.planStudiow = planStudiow;
//        this.przeszProgi = przeszProgi;
//        this.progPunktowy = progPunktowy;
//        this.prognLiczbaMiejsc = prognLiczbaMiejsc;
//        this.liczbaOsNaMiejsce = liczbaOsNaMiejsce;
//        this.cenaZaWniosek = cenaZaWniosek;
//        this.liczbaKandydWPoprzLat = liczbaKandydWPoprzLat;
//        this.liczbaChetnych = liczbaChetnych;
//        this.stopienStudiow = stopienStudiow;
//        this.wydzial = wydzial;
//    }
}
