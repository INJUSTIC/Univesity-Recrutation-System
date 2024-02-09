package com.study.PO.entities.kierunek.wskaznik;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "kryteria")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Kryterium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "Nazwa")
    @NotBlank(message = "Nazwa kryterium jest wymagana")
    private String nazwa;

    @OneToMany(mappedBy = "kryterium",
            cascade = CascadeType.ALL)
    private List<KryteriumWstepne> kryteria;
}