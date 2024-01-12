package com.study.PO.entities.dokument;

import com.study.PO.entities.wniosek.Wniosek;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dokumenty")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Dokument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "przelicznik")
    @Min(value = 0, message = "przelicznik musi być nieujemny")
    private double przelicznik;
    private boolean czyZweryfikowany;
    @Column(name = "typ_dokumentu")
    private TypDokumentu typDokumentu;
    @Column(name = "nazwa_dokumentu")
    private String nazwaDokumentu;

    @ManyToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="wniosek_id")
    private Wniosek wniosek;
}
