package edu.gdlc_project.gdlc_pckgs.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "documentmaterial")
public class DocumentMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String documentMaterialName;

    @Column(columnDefinition="TEXT")
    protected String documentMaterialUrl;

    protected Date documentMaterialDate;

    protected String documentMaterialDescription;

    @ManyToOne
    protected Design designGuide;
}
