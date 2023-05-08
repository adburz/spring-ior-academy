package pl.polsl.aei.ior.springdata.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="STUDENCI")
@Setter @Getter
public class Student {
     @Id
     private int nrStud;
     private String nazwisko;
     private Date dataUr;
     private String plec;
     private String nrKier;
}
