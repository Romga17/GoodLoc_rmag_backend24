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

    protected Date materialWarrantyEndDate;

    protected float materialAcquisitionPrice;

    protected String materialReference;

    @ManyToOne
    protected Category categoryMat;

    @ManyToOne
    protected Design designMat;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "picmaterial_material", joinColumns = @JoinColumn(name="material_id"),
            inverseJoinColumns = @JoinColumn(name="pic_material_id"))
    protected List<PicMaterial> picMaterialList = new ArrayList<>();

    @ManyToOne
    @Nullable
    //Ajout pour la relation avec IncidentNotification:
    @JoinColumn(name = "notified_material_id_incident_notification", referencedColumnName = "id_incident_notification")
    protected IncidentNotification notifiedMaterial;
}
