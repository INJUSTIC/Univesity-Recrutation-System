package com.study.PO.repositories;

import com.study.PO.entities.kierunek.Kierunek;

import com.study.PO.entities.kierunek.StopienStudiow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KierunekRepository extends JpaRepository<Kierunek, Long>{
    @Query(value = "SELECT DISTINCT K.Nazwa\n" +
            "FROM (kryteria_wstepne KW JOIN kryteria K ON KW.kryterium_id=K.id) JOIN kierunki ON KW.kierunek_id=kierunki.id\n" +
            "WHERE KW.kierunek_id IN (SELECT id FROM kierunki WHERE stopien_studiow=:stopienStudiow);", nativeQuery = true)
    List<String> findDistinctRodzajeKryteriowByStopienStudiow(String stopienStudiow);
}
