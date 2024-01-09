package com.study.PO.repositories;

import com.study.PO.entities.dokument.Dokument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DokumentRepository extends CrudRepository<Dokument, Long>{}
