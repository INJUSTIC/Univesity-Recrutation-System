package com.study.PO.repositories;

import com.study.PO.entities.kandydat.Kandydat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KandydatRepository extends CrudRepository<Kandydat, Long>{}
