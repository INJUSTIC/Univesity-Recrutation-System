//TODO czy będziemy jakoś sprawdzać język (np w jakim języku została napisana ulica)
//TODO powiaty oraz wojewodztwa jako enum
//TODO custom walidator dla sprawdzenia daty urodzenia (min n lat)

package com.study.PO.entities.wniosek.dane;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "dane_personalne")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DanePersonalne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "imie")
    @NotBlank(message = "imię jest wymagane")
    @Size(max = 128, message = "imię nie może przekraczać 128 znaków")
    private String imie;

    @Column (name = "nazwisko")
    @NotBlank(message = "nazwisko jest wymagane")
    @Size(max = 128, message = "nazwisko nie może przekraczać 128 znaków")
    private String nazwisko;

    @Column (name = "kraj_ur")
    @NotNull(message = "kraj urodzenia jest wymagany")
    private Kraj krajUr;

    @Email(message = "Email jest niepoprawny", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Size(max = 255, message = "Email nie musi przekraczać 255 znaków")
    @Column(name = "email")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @Column(name="data_ur")
    private Date dataUr;

    @Column (name = "miejsc_ur")
    @NotBlank(message = "miejscowość urodzenia jest wymagane")
    @Size(max = 35, message = "miejscowość urodzenia nie może przekraczać 35 znaków")
    private String miejscUr;

    @Column (name = "imie_matki")
    @Size(max = 20, message = "miejsce urodzenia nie może przekraczać 20 znaków")
    private String imieMatki;

    @Column (name = "imie_ojca")
    @Size(max = 20, message = "miejsce urodzenia nie może przekraczać 20 znaków")
    private String imieOjca;

    @Column (name = "kraj_zam")
    @NotNull(message = "kraj zamieszkania jest wymagany")
    private Kraj krajZam;

    @Column (name = "miejsc_zam")
    @NotBlank(message = "miejscowość zamieszkania jest wymagane")
    @Size(max = 35, message = "miejscowość zamieszkania nie może przekraczać 35 znaków")
    private String miejscZam;

    @Column (name = "ulica")
    @NotBlank(message = "ulica jest wymagana")
    @Size(max = 35, message = "ulica nie może przekraczać 35 znaków")
    private String ulica;

    @Column (name = "numer_domu")
    @NotBlank(message = "numer domu jest wymagany")
    @Size(max = 6, message = "numer domu nie może przekraczać 6 znaków")
    private String numerDomu;

    @Column (name = "numer_mieszk")
    @Size(max = 6, message = "numer mieszkania nie może przekraczać 6 znaków")
    private String numerMieszk;

    @Column (name = "kod_pocztowy")
    @NotBlank(message = "kod pocztowy jest wymagane")
    @Size(min = 5, max = 5, message = "kod pocztowy musi mieć 5 znaków")
    private String kodPocztowy;

    @Column (name = "powiat")
    @Size(max = 35, message = "miejscowość zamieszkania nie może przekraczać 35 znaków")
    private String powiat;

    @Column (name = "wojewodz")
    private String wojewodz;

    @Column (name = "numer_tel")
    @NotBlank(message = "numer telefonu jest wymagany")
    @Size(max = 14, message = "numer telefonu nie może przekraczać 14 znaków")
    private String numerTel;

    @Column (name = "obywatelstwo")
    @NotBlank(message = "obywatelstwo jest wymagane")
    @Size(max = 35, message = "obywatelstwo nie może przekraczać 35 znaków")
    private String obywatelstwo;

    @Column (name = "pesel")
    @Size(min = 11, max = 11, message = "pesel musi mieć 11 znaków")
    private String pesel;

    @Column (name = "numer_paszp")
    private String numerPaszp;
}
