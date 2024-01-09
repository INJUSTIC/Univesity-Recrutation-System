package com.study.PO.entities.kierunek;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "opiekunowie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Opiekun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "Imię")
    @NotBlank(message = "Imię jest wymagane")
    @Size(max = 128, message = "Imię nie może przekraczać 128 znaków")
    private String imie;

    @Column (name = "Nazwisko")
    @NotBlank(message = "Nazwisko jest wymagane")
    @Size(max = 128, message = "Nazwisko nie może przekraczać 128 znaków")
    private String nazwisko;
}
