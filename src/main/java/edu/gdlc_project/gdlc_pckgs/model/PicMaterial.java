package edu.gdlc_project.gdlc_pckgs.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "picmaterial")
public class PicMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String picMaterialName;

    @Column(columnDefinition="TEXT")
    protected String picMaterialUrl;
}
