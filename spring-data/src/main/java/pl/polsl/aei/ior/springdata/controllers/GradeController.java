package pl.polsl.aei.ior.springdata.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.polsl.aei.ior.springdata.entities.Grade;
import pl.polsl.aei.ior.springdata.repositories.GradeRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {
    @Autowired
    GradeRepository gradeRepository;

    //http://localhost:8081/grades
    //Gets all grades from OCENY table.
    @GetMapping()
    List<Grade> getAll()
    {
        return gradeRepository.findAll();
    }

    //http://localhost:8081/grades/pageable
    //Gets all grades from OCENY table but in pageable form.
    @GetMapping("/pageable")
    Page<Grade> getAllPaginated(Pageable page)
    {
        return gradeRepository.findAll(page);
    }

    //http://localhost:8081/grades/find-by-nrStud-and-nrPrzed
    //Returns grade which matches request params 'nrStud' and 'nrPrzed'
    @GetMapping("/find-by-nrStud-and-nrPrzed")
    Grade getByNrStudAndNrPrzedm(
            @RequestParam int ocena,
            @RequestParam Boolean zalEgz)
    {
        return gradeRepository.findByOcenaAndZalEgz(ocena, zalEgz);
    }

    //http://localhost:8081/grades/pageable/ocena-zal-egz
    //Returns grade which matches request params 'nrStud' and 'nrPrzed'
    @GetMapping("/pageable/ocena-zal-egz")
    Page<Grade> getPaginatedByNrStudAndNrPrzedm(
            Pageable page,
            @RequestParam int ocena,
            @RequestParam Boolean zalEgz)
    {
        return gradeRepository.findByOcenaAndZalEgz(page, ocena, zalEgz);
    }

    //http://localhost:8081/grades/pageable/data-zal
    //Returns paginable list of grades from OCENY table based on 'DataZal' requestParam which at least partially match provided dataZal
    @GetMapping("/pageable/data-zal")
    Page<Grade> getPaginatedByNrStudAndNrPrzedm(
            Pageable page,
            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd 00:00:00") Date dataZal)
    {
        return gradeRepository.findByDataZalLike(page, dataZal);
    }

    //http://localhost:8081/grades/get-distinct
    //Returns distinct grades list using filtration from request params 'nrStud', 'nrPrzedm' or 'termin'.
    @GetMapping("/get-distinct")
    List<Grade> getDistinctGradeByNrStudAndNrPrzedm(@RequestParam int nrStud, @RequestParam int nrPrzedm,@RequestParam int termin)
    {
        return gradeRepository.findDistinctByNrStudAndNrPrzedmOrTermin(nrStud, nrPrzedm, termin);
    }

    //http://localhost:8081/grades/pageable/get-distinct
    //Returns paginable distinct grades list using filtration from request params 'nrStud', 'nrPrzedm' or 'termin'.
    @GetMapping("/pageable/get-distinct")
    Page<Grade> getDistinctGradeByNrStudAndNrPrzedm(Pageable page, @RequestParam int nrStud, @RequestParam int nrPrzedm,
                                                    @RequestParam int termin)
    {
        return gradeRepository.findDistinctByNrStudAndNrPrzedmOrTermin(nrStud,nrPrzedm,termin,page);
    }

    //http://localhost:8081/grades/pageable/get-distinct
    //Returns paginable list of grades which match provided date but with use of custom Query.
    @GetMapping("/query")
    Page<Grade> getPageByDataZal(Pageable page, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd 00:00:00") Date dataZal)
    {
        return gradeRepository.findByDataZal(page, dataZal);
    }

    //http://localhost:8081/grades/delete
    //DELETES stuff who could've guessed!??!?
    @DeleteMapping()
    @Transactional()
    @Modifying()
    void deleteGradeByDataZal(@RequestParam() @DateTimeFormat(pattern="yyyy-MM-dd 00:00:00") Date dataZal)
    {
        gradeRepository.deleteByDataZal(dataZal);
    }
}