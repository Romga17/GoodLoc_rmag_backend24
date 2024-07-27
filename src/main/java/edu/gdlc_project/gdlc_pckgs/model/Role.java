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

    protected String roleName;

    @Column(columnDefinition="TEXT")
    protected String roleDescription;

    protected String roleState;


    @ManyToMany
    @JoinTable(name = "category_role", joinColumns = @JoinColumn(name="role_id"),
            inverseJoinColumns = @JoinColumn(name="category_id"))
    protected List<Category> categoryList = new ArrayList<>();
}
