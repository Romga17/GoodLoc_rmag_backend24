package edu.gdlc_project.gdlc_pckgs.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "declaration_incident")
public class DeclarationIncident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_declaration_incident")
    protected int id;

    @Column(columnDefinition = "DATE")
    protected Date date_declaration;

    @Column(name = "type_incident")
    protected String typeIncident;

    @Column(name = "description_declaration_incident")
    protected String descriptionIncident;

}
