package pl.polsl.aei.ior.springdata.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRZEDMIOTY")
@Setter @Getter @NoArgsConstructor
public class Subject {
    @Id
    private int nrPrzedm;
    private String nazwaPrzedm;
    @Column(name="KOD_TPRZEDM")
    private String kodTPrzedm;
    private Integer nrPrzedmNadrz;
    private int nrOdpPrac;
    private int nrKier;

}
