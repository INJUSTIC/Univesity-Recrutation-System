package com.study.PO.entities.wniosek.dane;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "dane_dodatkowe_inz")
public class DaneDodatkoweInz extends DaneDodatkowe{
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    @Column(name="data_uk_szk_sr")
    private Date DataUkSzkSr;

    @Column(name = "nr_swiad_dojrz")
    @NotBlank(message = "numer świadectwa dojrzałości jest wymagany")
    @Size(max = 15, message = "nr_swiad_dojrz nie może przekraczać 15 znaków")
    private String NrSwiadDojrz;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @Column(name="data_wyst_swiad_dojrz")
    private Date DataWystSwiadDojrz;

    @Column(name = "uczelnia_ukoncz_szk_sr")
    @NotBlank(message = "uczelnia ukończenia szkoły średniej jest wymagana")
    @Size(max = 60, message = "uczelnia ukończenia szkoły średniej nie może przekraczać 60 znaków")
    private String UczelniaUkonczSzkSr;
}
