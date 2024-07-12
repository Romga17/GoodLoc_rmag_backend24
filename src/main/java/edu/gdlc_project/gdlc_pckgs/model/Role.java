package edu.gdlc_project.gdlc_pckgs.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

    public Role() {
        this.id = 1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String nom;

    @Column(columnDefinition="TEXT")
    protected String description;

    protected String statut;


    @ManyToMany
    @JoinTable(name = "categorie_role", joinColumns = @JoinColumn(name="role_id"),
            inverseJoinColumns = @JoinColumn(name="categorie_id"))
    protected List<Categorie> categorieList = new ArrayList<>();
}
