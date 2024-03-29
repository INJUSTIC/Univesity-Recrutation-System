package com.study.PO.repositories;

import com.study.PO.entities.kierunek.Kierunek;

import com.study.PO.entities.kierunek.StopienStudiow;
import com.study.PO.entities.kierunek.wskaznik.Kryterium;
import com.study.PO.entities.kierunek.wskaznik.MaturalnyPrzedmiotDodatkowy;
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

    @Query(value = "SELECT MPD.id\n" +
            "FROM ((kierunki K JOIN kryteria_wstepne KW ON K.id=KW.kierunek_id) JOIN kryteria Kr ON KW.kryterium_id=Kr.id) JOIN maturalne_przedmioty_dodatkowe MPD ON Kr.id=MPD.id\n" +
            "WHERE K.nazwa=:nazwaKierunku", nativeQuery = true)
    Long findMPDbyNazwaKierunku(String nazwaKierunku);

    @Query(value = "SELECT KL.id\n" +
            "FROM ((kierunki K JOIN kryteria_wstepne KW ON K.id=KW.kierunek_id) JOIN kryteria Kr ON KW.kryterium_id=Kr.id) JOIN kryteria_lekarski KL ON Kr.id=KL.id\n" +
            "WHERE K.nazwa=:nazwaKierunku", nativeQuery = true)
    Long findKLbyNazwaKierunku(String nazwaKierunku);

    @Query(value = "SELECT * FROM kierunki WHERE stopien_studiow=:stopienStudiow", nativeQuery = true)
    List<Kierunek> findKierunkiByStopienStudiow(String stopienStudiow);

}
