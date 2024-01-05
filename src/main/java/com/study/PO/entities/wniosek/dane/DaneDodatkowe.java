package com.study.PO.entities.wniosek.dane;

import jakarta.persistence.*;
@Entity
@Table (name = "dane_dodatkowe")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DaneDodatkowe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
