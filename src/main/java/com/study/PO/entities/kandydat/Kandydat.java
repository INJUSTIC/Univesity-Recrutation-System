package com.study.PO.entities.kandydat;

import com.study.PO.entities.dokument.Dokument;
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
@Table(name = "kandydaci")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Kandydat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login")
    @NotBlank(message = "login jest wymagany")
    @Size(max = 128, message = "login nie może przekraczać 128 znaków")
    private String login;

    @Column(name = "haslo")
    @NotBlank(message = "hasło jest wymagane")
    @Size(max = 128, message = "hasło nie może przekraczać 128 znaków")
    private String haslo;

    @OneToMany(mappedBy = "kandydat",
            cascade = CascadeType.ALL)
    private List<Wniosek> wnioski;
}
