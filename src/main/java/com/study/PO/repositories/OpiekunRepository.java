package com.study.PO.repositories;

import com.study.PO.entities.kandydat.Kandydat;
import com.study.PO.entities.kierunek.Opiekun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpiekunRepository extends JpaRepository<Opiekun, Long> {}
