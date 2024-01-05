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

    @Column (name = "imie")
    @NotBlank(message = "imię jest wymagane")
    @Size(max = 128, message = "imię nie może przekraczać 128 znaków")
    private String imie;

    @Column (name = "nazwisko")
    @NotBlank(message = "nazwisko jest wymagane")
    @Size(max = 128, message = "nazwisko nie może przekraczać 128 znaków")
    private String nazwisko;
}
