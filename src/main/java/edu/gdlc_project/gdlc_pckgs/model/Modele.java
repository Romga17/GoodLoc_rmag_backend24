package edu.gdlc_project.gdlc_pckgs.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "modele")
public class Modele {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String modele;

    protected String descriptionmodele;

    @ManyToOne
    protected Marque brand;
}

