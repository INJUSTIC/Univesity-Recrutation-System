package com.study.PO.repositories;

import com.study.PO.entities.wydzial.Wydzial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WydzialRepository extends JpaRepository<Wydzial, Long>{}
