package com.study.PO.repositories;

import com.study.PO.entities.dokument.Dokument;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DokumentRepository extends JpaRepository<Dokument, Long>{}
