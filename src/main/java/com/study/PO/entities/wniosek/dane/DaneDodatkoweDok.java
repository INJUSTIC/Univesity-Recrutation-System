package com.study.PO.entities.wniosek.dane;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@Table (name = "dane_dodatkowe_doktor")
public class DaneDodatkoweDok extends DaneDodatkowe {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    @Column(name="data_uk_stud_II_stop")
    private Date dataUkStud_II_Stop;

    @Column(name = "kierunek_ukoncz_stud_II_stop")
    @NotBlank(message = "kierunek ukończenia studiów II stopnia jest wymagany")
    @Size(max = 60, message = "kierunek ukończenia studiów II stopnia nie może przekraczać 60 znaków")
    private String KierunekUkonczStud_II_Stop;

    @Column(name = "uczelnia_ukoncz_stud_II_stop")
    @NotBlank(message = "uczelnia ukończenia studiów II stopnia jest wymagany")
    @Size(max = 60, message = "uczelnia ukończenia studiów II stopnia nie może przekraczać 60 znaków")
    private String UczelniaUkonczStud_II_Stop;
}
