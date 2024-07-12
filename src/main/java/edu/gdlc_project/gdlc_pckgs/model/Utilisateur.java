package edu.gdlc_project.gdlc_pckgs.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.gdlc_project.gdlc_pckgs.view.UtilisateurView;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(UtilisateurView.class)
    protected int id;

    @JsonView(UtilisateurView.class)
    @NotBlank(message="Renseigner le nom est obligatoire")
    @Size(min = 2)
    @Pattern(regexp = "^[a-zA-Z -]+$")
    protected String nom;

    @JsonView(UtilisateurView.class)
    @NotBlank(message="Renseigner le prenom est obligatoire")
    @Size(min = 2)
    @Pattern(regexp = "^[a-zA-Z -]+$")
    protected String prenom;

    @JsonView(UtilisateurView.class)
    @NotBlank(message="Renseigner l'email est obligatoire")
    @Pattern(regexp = "^[a-zA-Z@ ._-]+$")
    @Column(unique=true)
    protected String email;

    @NotBlank(message="Renseigner le type de voie est obligatoire")
    @Pattern(regexp = "^[a-zA-Z -]+$")
    protected String typeVoie;

    @NotBlank(message="Renseigner l'adresse est obligatoire")
    @Pattern(regexp = "^[a-zA-Z -]+$")
    protected String adresse;

    @NotBlank(message="Renseigner le numéro d'adresse est obligatoire")
    @Pattern(regexp = "^[0-9a-zA-Z .-]+$")
    protected String numeroAdresse;

    @NotBlank(message="Renseigner le code postal est obligatoire")
    @Pattern(regexp = "^[0-9 .-]+$")
    @Size(min = 5)
    protected String codePostal;

    @NotBlank(message="Renseigner le code postal est obligatoire")
    @Pattern(regexp = "^[a-zA-Z- ]+$")
    protected String ville;

    @NotBlank(message="Renseigner un numéro de téléphone est obligatoire")
    @Pattern(regexp = "^[0-9a-zA-Z +-]+$")
    @Size(min = 10)
    protected String telephone;


    @NotBlank(message="Renseigner le mot de passe est obligatoire")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[#?!@$%^&*-]).{8,}$")
    protected String motDePasse;

    @DateTimeFormat
    protected LocalDate dateEntree;

    @DateTimeFormat
    protected LocalDate dateSortie;

    @JsonView(UtilisateurView.class)
    @NotBlank(message="Renseigner votre cursus est obligatoire")
    @Pattern(regexp = "^[0-9a-zA-Z _#-]+$")
    protected String cursus;

    //@JsonView(UtilisateurView.class)
    //protected boolean administrateur;

    /*@ManyToMany
    @JoinTable(name = "role_utilisateur", joinColumns = @JoinColumn(name="utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    protected List<Role> roleList = new ArrayList<>();*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonView(Utilisateur.class)
    @JoinTable(name = "materiel_utilisateur", joinColumns = @JoinColumn(name="utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name="materiel_id"))
    protected List<Materiel> materielList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "etablissement_utilisateur", joinColumns = @JoinColumn(name="utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name="etablissement_id"))
    protected List<Etablissement> etablissementList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    protected Role userRole;

    @ManyToMany(fetch = FetchType.EAGER)
    @Nullable
    @JsonView(Utilisateur.class)
    @JoinTable(name = "declaration_incident_utilisateur", joinColumns = @JoinColumn(name="utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name="id_declaration_incident"))
    protected List <DeclarationIncident> declarationUtil = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilisateur)) return false;
        Utilisateur utilisateur = (Utilisateur) o;
        return getNom().equals(utilisateur.getNom()) &&
                getPrenom().equals(utilisateur.getPrenom()) &&
                getEmail().equals(utilisateur.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNom(), getPrenom(), getEmail());
    }
}
