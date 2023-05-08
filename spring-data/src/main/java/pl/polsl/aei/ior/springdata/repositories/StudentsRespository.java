package pl.polsl.aei.ior.springdata.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.polsl.aei.ior.springdata.entities.Student;

import java.util.Date;
import java.util.List;

public interface StudentsRespository extends JpaRepository<Student, Integer> {
    List<Student> findAll();
    //1. Wyszukiwanie po dwóch wybranych atrybutach (naraz)
    Student findByNazwiskoAndPlec(String nazwisko, String plec);
    //2. j.w. z możliwością sortowania i paginacji
    Page<Student> findAllByNazwiskoAndPlec(Pageable page, String nazwisko, String plec);
    //3. Wyszukiwanie po atrybutach z klauzulą Like oraz IgnoreCase
    Page<Student> findByNazwiskoIgnoreCaseAndDataUrLike(Pageable page, String nazwisko, Date dataUr);
    //4. Wyszukiwania po atrybutach z klauzulą Distics, And oraz OR
    Page<Student> findDistinctStudentByNazwiskoAndPlecOrDataUr(Pageable page, String nazwisko, String plec, Date dataUr);
    //5. Z adnotacją @Query z parametrami
    @Query(value = "SELECT s FROM Student s WHERE s.nrKier = ?1")
    List<Student> findAllStudentsByNrKier(String nrKier);
    //Usuwanie wskazanego wiersza po Id.
    void deleteById(Integer id);
}