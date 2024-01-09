package com.study.PO.repositories;

import com.study.PO.entities.wniosek.Wniosek;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WniosekRepository extends CrudRepository<Wniosek, Long>{}
