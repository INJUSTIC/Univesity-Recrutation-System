package com.study.PO.entities.wydzial;

import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.entities.wniosek.Wniosek;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "wydzialy")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wydzial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "nazwa")
    @NotBlank(message = "nazwa jest wymagana")
    @Size(max = 100, message = "nazwa nie może przekraczać 100 znaków")
    private String nazwa;

    @OneToMany(mappedBy = "wydzial",
            cascade = CascadeType.ALL)
    private List<Kierunek> kierunki;
}
