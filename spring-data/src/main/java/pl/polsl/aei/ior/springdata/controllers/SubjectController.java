package pl.polsl.aei.ior.springdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.polsl.aei.ior.springdata.entities.Subject;
import pl.polsl.aei.ior.springdata.repositories.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    //http://localhost:8081/subjects
    //Returns all records from table PRZEDMIOTY
    @GetMapping()
    List<Subject> getAll()
    {
        return subjectRepository.findAll();
    }

    //http://localhost:8081/subjects/pageable
    //Returns all records from table PRZEDMIOTY but in paginable form
    @GetMapping("/pageable")
    Page<Subject> getAllPaginated(Pageable page)
    {
        return subjectRepository.findAll(page);
    }

    //http://localhost:8081/subjects/between
    //Returns all records from table PRZEDMIOTY which have subjectCode between min and max
    @GetMapping("/between")
    List<Subject> getSubjectsByKodTPrzedmBetween(@RequestParam int minKodTPrzedm, @RequestParam int maxKodTPrzedm)
    {
        return subjectRepository.findByKodTPrzedmBetween(minKodTPrzedm, maxKodTPrzedm);
    }

    //http://localhost:8081/subjects/pageable/between
    //Returns all records from table PRZEDMIOTY which have subjectCode between 'minNrPrzedm' and 'maxNrPrzedm' in paginable form
    @GetMapping("/pageable/between")
    Page<Subject> getSubjectsByNrPrzedmBetween(@RequestParam int minNrPrzedm, @RequestParam int maxNrPrzedm, Pageable page)
    {
        return subjectRepository.findByNrPrzedmBetween(page, minNrPrzedm, maxNrPrzedm);
    }

    //http://localhost:8081/subjects/pageable/find-like
    //Returns all records from table PRZEDMIOTY which have SubjectName matching 'nazwaPrzedm'.
    @GetMapping("/pageable/find-like")
    Page<Subject> getSubjectByNameLike(@RequestParam String nazwaPrzedm, Pageable page)
    {
        return subjectRepository.findByNazwaPrzedmIgnoreCaseAndNazwaPrzedmLike(page, nazwaPrzedm, nazwaPrzedm);
    }

    //http://localhost:8081/subjects/pageable/find-distinct
    //Returns distinct subject using giltration from request params 'nrKier' and 'kodTPrzedm' or 'nrOdpPrac'.
    @GetMapping("/pageable/find-distinct")
    Page<Subject> getDistinctSubjectByNrKierAndKodTPrzedmOrNrOdpPrac(
            Pageable page,
            @RequestParam int nrKier,
            @RequestParam String kodTPrzedm,
            @RequestParam int nrOdpPrac)
    {
        return subjectRepository.findDistinctByNrKierAndKodTPrzedmOrNrOdpPrac(page, nrKier, kodTPrzedm, nrOdpPrac);
    }

    //http://localhost:8081/subjects/find-similar
    //Returns paginated Subjects list using filtration from request param 'nameToMatch' using custom Query.
    @GetMapping("/find-similar")
    List<Subject> getSubjectsMatchingName(@RequestParam String nameToMatch)
    {
        return subjectRepository.findAllSubjectsWithNameLike(nameToMatch);
    }

    //http://localhost:8081/subjects/delete
    //Deletes subject from table PRZEDMIOTY based on passed id
    @DeleteMapping()
    void deleteSubjectById(@RequestParam int id)
    {
        subjectRepository.deleteById(id);
    }
}
