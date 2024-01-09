package com.study.PO.repositories;

import com.study.PO.entities.kierunek.Kierunek;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KierunekRepository extends CrudRepository<Kierunek, Long>{}
