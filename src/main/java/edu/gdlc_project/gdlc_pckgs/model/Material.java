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
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(BookingRequest.class)
    protected Integer id;

    protected String materialStatus;

    @Column(columnDefinition="TEXT")
    protected String materialDescription;

    protected Date materialAcquisitionDate;

    protected Date MaterialGuaranteeEndDate;

    protected float materialAcquisitionPrice;

    protected String materialReference;

    /*@ManyToOne(fetch = FetchType.EAGER)
    protected DemandeReservation demandereservationmateriel;*/

    /*@ManyToMany
    @JoinTable(name = "etat_materiel")
    protected List<Etat> etatList = new ArrayList<>();*/

    @ManyToOne
    protected Category categoryMat;

    @ManyToOne
    protected Design designMat;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "imagemat_materiel", joinColumns = @JoinColumn(name="materiel_id"),
            inverseJoinColumns = @JoinColumn(name="imagemat_id"))
    protected List<PicMaterial> picMaterialList = new ArrayList<>();

    @ManyToOne
    @Nullable
    //Ajout pour liaison avec DeclarationIncident
    @JoinColumn(name = "notified_material_id_incident_notification", referencedColumnName = "id_incident_notification")
    protected IncidentNotification notifiedMaterial;




}
