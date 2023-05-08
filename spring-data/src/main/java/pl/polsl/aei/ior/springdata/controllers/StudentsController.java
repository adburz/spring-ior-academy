package pl.polsl.aei.ior.springdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.polsl.aei.ior.springdata.entities.Student;
import pl.polsl.aei.ior.springdata.repositories.StudentsRespository;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    StudentsRespository repository;

    //http://localhost:8081/students
    //Returns all records from table STUDENCI
    @GetMapping()
    List<Student> getAll(){
        return repository.findAll();
    }

    //http://localhost:8081/students/nazwisko-plec
    //Returns Student who matches request params 'nazwisko' and 'plec'.
    @GetMapping("/nazwisko-plec")
    Student getStudentByNazwiskoAndPlec(
            @RequestParam(required = false) String nazwisko,
            @RequestParam(required = false) String plec){
        return repository.findByNazwiskoAndPlec(nazwisko, plec);
    }

    //http://localhost:8081/students/pageable
    //Returns paginated Students list using filtration from request params 'page', 'nazwisko' and 'plec'.
    @GetMapping("/pageable")
    Page<Student> getStudentsPageByNazwiskoAndPlec(
            Pageable page,
            @RequestParam(required = false) String nazwisko,
            @RequestParam(required = false) String plec){
        return repository.findAllByNazwiskoAndPlec(page, nazwisko, plec);
    }

    //http://localhost:8081/students/nazwisko-data-ur
    //Returns paginated Students list using filtration from request params 'page', 'nazwisko' and 'dataUr'.
    @GetMapping("/nazwisko-data-ur")
    Page<Student> getStudentsPageByNazwiskoAndDataUr(
            Pageable page,
            @RequestParam(required = false) String nazwisko,
            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd 00:00:00") Date dataUr){
       return repository.findByNazwiskoIgnoreCaseAndDataUrLike(page, nazwisko, dataUr);
    }

    //http://localhost:8081/students/nazwisko-plec-data-ur/distinct
    //Returns paginated distinct Students list using filtration from request params 'page', 'nazwisko' and 'plec' or 'dataUr'.
    @GetMapping("/nazwisko-plec-data-ur/distinct")
    Page<Student> getDistinctStudentsPageByNazwiskoAndPlecOrDataUr(
            Pageable page,
            @RequestParam(required = false) String nazwisko,
            @RequestParam(required = false) String plec,
            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd 00:00:00") Date dataUr){

        if(dataUr != null )
            return repository.findDistinctStudentByNazwiskoAndPlecOrDataUr(page, nazwisko, plec, dataUr);

        if(nazwisko.isEmpty() || plec.isEmpty() )
            throw new IllegalArgumentException("Nazwisko and Plec or DataUr must be provided.");

       return repository.findDistinctStudentByNazwiskoAndPlecOrDataUr(page, nazwisko, plec, dataUr);
    }

    //http://localhost:8081/students/query
    //Returns Students list using filtration from request params 'nrKier' using custom Query.
    @GetMapping("/query")
    List<Student> getStudentsByNrKier(@RequestParam(required = false) String nrKier){
        return repository.findAllStudentsByNrKier(nrKier);
    }

    //http://localhost:8081/students
    //Deletes Student from table STUDENCI based on passed Id.
    @DeleteMapping
    void delete (@RequestParam int id){
        repository.deleteById(id);
    }
}
