package com.study.PO.repositories;

import com.study.PO.entities.kierunek.Kierunek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KierunekRepository extends JpaRepository<Kierunek, Long>{}
