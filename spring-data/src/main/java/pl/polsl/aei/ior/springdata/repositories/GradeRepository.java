package pl.polsl.aei.ior.springdata.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.polsl.aei.ior.springdata.entities.Grade;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

    Page<Grade> findAll(Pageable page);

//    //1. Wyszukiwania po dwóch wybranych atrybutach (naraz)
    Grade findByOcenaAndZalEgz(int ocena, Boolean zalEgz);

    //2. J.w. z możliwością sortowania i paginacji
    Page<Grade> findByOcenaAndZalEgz(Pageable page, int ocena, Boolean zalEgz);

    //3. Wyszukiwania po atrybutach z klauzulą Like oraz IgnoreCase (w tym wypadku IgnoreCase nie zostanie uzyty)
    Page<Grade> findByDataZalLike(Pageable page, Date dataZal);

    //4. Wyszukiwania po atrybutach z klauzulą Distinct, And oraz Or
    List<Grade> findDistinctByNrStudAndNrPrzedmOrTermin(int nrStud, int nrPrzedm, int termin);

    //4. Wyszukiwania po atrybutach z klauzulą Distinct, And oraz Or
    Page<Grade> findDistinctByNrStudAndNrPrzedmOrTermin(int nrStud, int nrPrzedm, int termin, Pageable page);

    //5. Z adnotacją @Query z parametrami
    @Query(value="Select g from Grade g where g.dataZal = :dataZal ")
    Page<Grade> findByDataZal(Pageable page, @Param("dataZal") Date dataZal);

    void deleteByDataZal(Date dataZal);
}
