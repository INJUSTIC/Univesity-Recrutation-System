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
@Table(name = "dane_dodatkowe_mag")
public class DaneDodatkoweMag extends DaneDodatkowe{
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    @Column(name="data_uk_stud_I_stop")
    private Date dataUkStud_I_Stop;

    @Column(name = "kierunek_ukoncz_stud_I_stop")
    @NotBlank(message = "kierunek ukończenia studiów I stopnia jest wymagany")
    @Size(max = 60, message = "kierunek ukończenia studiów I stopnia nie może przekraczać 60 znaków")
    private String KierunekUkonczStud_I_Stop;

    @Column(name = "uczelnia_ukoncz_stud_I_stop")
    @NotBlank(message = "uczelnia ukończenia studiów I stopnia jest wymagany")
    @Size(max = 60, message = "uczelnia ukończenia studiów I stopnia nie może przekraczać 60 znaków")
    private String UczelniaUkonczStud_I_Stop;
}
