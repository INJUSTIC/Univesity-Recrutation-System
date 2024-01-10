package com.study.PO.repositories;

import com.study.PO.entities.kandydat.Kandydat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KandydatRepository extends JpaRepository<Kandydat, Long>{}
