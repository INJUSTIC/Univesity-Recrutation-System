package com.study.PO.repositories;

import com.study.PO.entities.wydzial.Wydzial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WydzialRepository extends CrudRepository<Wydzial, Long>{}
