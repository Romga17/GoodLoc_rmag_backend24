package edu.gdlc_project.gdlc_pckgs.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@Entity
@Table(name = "demande_reservation")
public class DemandeReservation {

    public DemandeReservation() {
        this.validation = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Utilisateur.class)
    protected int id;

    protected boolean validation;

    protected Date dateDebut;

    protected Date dateFin;

    @Nullable
    protected String motifRefus;

    protected Date dateDemande;

    @Column(columnDefinition="TEXT")
    @Nullable
    protected String messageUser;

    @Nullable
    protected Date dateAccord;

    @ManyToOne
    protected Utilisateur demandeur;

    @ManyToOne
    @Nullable
    protected Utilisateur validateur;

    @ManyToOne
    protected Materiel materielReserv;
    //protected Materiel materiel;


    @Override
    public String toString() {
        return "DemandeReservation{" +
                "dateAccord=" + dateAccord +
                ", dateDebut=" + dateDebut +
                ", dateDemande=" + dateDemande +
                ", dateFin=" + dateFin +
                ", demandeur=" + demandeur +
                ", id=" + id +
                ", materiel=" + materielReserv +
                ", messageUser='" + messageUser + '\'' +
                ", motifRefus='" + motifRefus + '\'' +
                ", validateur=" + validateur +
                ", validation=" + validation +
                '}';
    }
}
