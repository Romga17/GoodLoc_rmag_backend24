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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(UtilisateurView.class)
    protected int id;

    @JsonView(UtilisateurView.class)
    @NotBlank(message="Renseigner le nom est obligatoire")
    @Size(min = 2)
    @Pattern(regexp = "^[a-zA-Z -]+$")
    protected String userLastname;

    @JsonView(UtilisateurView.class)
    @NotBlank(message="Renseigner le prenom est obligatoire")
    @Size(min = 2)
    @Pattern(regexp = "^[a-zA-Z -]+$")
    protected String userFirstname;

    @JsonView(UtilisateurView.class)
    @NotBlank(message="Renseigner l'email est obligatoire")
    @Pattern(regexp = "^[a-zA-Z@ ._-]+$")
    @Column(unique=true)
    protected String userEmail;

    @NotBlank(message="Renseigner le type de voie est obligatoire")
    @Pattern(regexp = "^[a-zA-Z -]+$")
    protected String userRoadType;

    @NotBlank(message="Renseigner l'adresse est obligatoire")
    @Pattern(regexp = "^[a-zA-Z -]+$")
    protected String userAdress;

    @NotBlank(message="Renseigner le numéro d'adresse est obligatoire")
    @Pattern(regexp = "^[0-9a-zA-Z .-]+$")
    protected String userAdressNumber;

    @NotBlank(message="Renseigner le code postal est obligatoire")
    @Pattern(regexp = "^[0-9 .-]+$")
    @Size(min = 5)
    protected String userZipCode;

    @NotBlank(message="Renseigner le code postal est obligatoire")
    @Pattern(regexp = "^[a-zA-Z- ]+$")
    protected String userCity;

    @NotBlank(message="Renseigner un numéro de téléphone est obligatoire")
    @Pattern(regexp = "^[0-9a-zA-Z +-]+$")
    @Size(min = 10)
    protected String userPhone;


    @NotBlank(message="Renseigner le mot de passe est obligatoire")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[#?!@$%^&*-]).{8,}$")
    protected String userPassword;

    @DateTimeFormat
    protected LocalDate userArrivalDate;

    @DateTimeFormat
    protected LocalDate userDepartureDate;

    @JsonView(UtilisateurView.class)
    @NotBlank(message="Renseigner votre cursus est obligatoire")
    @Pattern(regexp = "^[0-9a-zA-Z _#-]+$")
    protected String userCourse;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonView(User.class)
    @JoinTable(name = "material_user", joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="material_id"))
    protected List<Material> userMaterialList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "location_user", joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="location_id"))
    protected List<Location> userLocationList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    protected Role userRole;

    @ManyToMany(fetch = FetchType.EAGER)
    @Nullable
    @JsonView(User.class)
    @JoinTable(name = "incident_notification_user", joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="id_incident_notification"))
    protected List <IncidentNotification> userIncidentNotification = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserLastname().equals(user.getUserLastname()) &&
                getUserFirstname().equals(user.getUserFirstname()) &&
                getUserEmail().equals(user.getUserEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserLastname(), getUserFirstname(), getUserEmail());
    }
}
