package edu.gdlc_project.gdlc_pckgs.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "materiel")
public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(DemandeReservation.class)
    protected Integer id;

    protected String statut;

    @Column(columnDefinition="TEXT")
    protected String description;

    protected Date dateAchat;

    protected Date dateFingarantie;

    protected float prixAchat;

    protected String reference;

    /*@ManyToOne(fetch = FetchType.EAGER)
    protected DemandeReservation demandereservationmateriel;*/

    /*@ManyToMany
    @JoinTable(name = "etat_materiel")
    protected List<Etat> etatList = new ArrayList<>();*/

    @ManyToOne
    protected Categorie categorieMat;

    @ManyToOne
    protected Modele modeleMat;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "imagemat_materiel", joinColumns = @JoinColumn(name="materiel_id"),
            inverseJoinColumns = @JoinColumn(name="imagemat_id"))
    protected List<MaterielImage> materielImageList = new ArrayList<>();

    @ManyToOne
    @Nullable
    //Ajout pour liaison avec DeclarationIncident
    @JoinColumn(name = "declaration_mat_id_declaration_incident", referencedColumnName = "id_declaration_incident")
    protected DeclarationIncident declarationMat;




}
