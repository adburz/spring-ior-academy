package pl.polsl.aei.ior.springdata.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.polsl.aei.ior.springdata.entities.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Page<Subject> findAll(Pageable page);
    List<Subject> findAll();

    //1. Wyszukiwania po dwóch wybranych atrybutach (naraz)
    List<Subject> findByKodTPrzedmBetween(int minKodTPrzedm, int maxKodTPrzedm);

    //2. J.w. z możliwością sortowania i paginacj
    Page<Subject> findByNrPrzedmBetween(Pageable page, int minNrPrzedm, int maxNrPrzedm);

    //3. Wyszukiwania po atrybutach z klauzulą Like oraz IgnoreCase
    Page<Subject> findByNazwaPrzedmIgnoreCaseAndNazwaPrzedmLike(Pageable page, String nazwaPrzedm, String nazwaPrzedm1);

    //4. Wyszukiwania po atrybutach z klauzulą Distinct, And oraz Or
    Page<Subject> findDistinctByNrKierAndKodTPrzedmOrNrOdpPrac(Pageable page, int nrKier, String kodTPrzedm, int nrOdpPrac);

    //5. Z adnotacją @Query z parametrami
    @Query(value = "SELECT sub FROM Subject sub WHERE sub.nazwaPrzedm LIKE %:nameMatch%")
    List<Subject> findAllSubjectsWithNameLike(@Param("nameMatch") String nameToBeMatched);

}
