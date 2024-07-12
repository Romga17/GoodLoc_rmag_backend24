package edu.gdlc_project.gdlc_pckgs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class EtatMateriel {

    @Id
    private Long id;

    protected Date  dateChangementEtat;

    @ManyToOne
    protected Etat etatmat;

    @ManyToOne
    protected Materiel materieltat;

}
