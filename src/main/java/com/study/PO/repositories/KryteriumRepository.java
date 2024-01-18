package com.study.PO.repositories;

import com.study.PO.entities.kierunek.wskaznik.Kryterium;
import com.study.PO.entities.wniosek.Wniosek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KryteriumRepository extends JpaRepository<Kryterium, Long> {}
