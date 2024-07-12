package edu.gdlc_project.gdlc_pckgs.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "documentmat")
public class MaterielDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String nomDocument;

    @Column(columnDefinition="TEXT")
    protected String urlDocument;

    protected Date dateDocument;

    protected String descriptionDocument;

    @ManyToOne
    protected Modele modeleGuide;
}
