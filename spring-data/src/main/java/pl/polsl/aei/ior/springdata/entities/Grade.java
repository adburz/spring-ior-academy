package pl.polsl.aei.ior.springdata.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="OCENY")
@Setter @Getter @NoArgsConstructor
public class Grade {
    @Id
    private Date dataZal;
    private int termin;
    private int nrStud;
    private int nrPrzedm;
    private int ocena;
    private Boolean zalEgz;
}