package edu.gdlc_project.gdlc_pckgs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(UtilisateurView.class)
    protected int id;

    @JsonView(UtilisateurView.class)
    @JsonProperty("userLastname")
    @NotBlank(message="Renseigner le nom est obligatoire")
    @Size(min = 2)
    @Pattern(regexp = "^[a-zA-Z -]+$")
    protected String userLastname;

    @JsonView(UtilisateurView.class)
    @NotBlank(message="Renseigner le prenom est obligatoire")
    @Size(min = 2)
    @Pattern(regexp = "^[a-zA-Z -]+$")
    @JsonProperty("userFirstname")
    protected String userFirstname;

    @JsonView(UtilisateurView.class)
    @NotBlank(message="Renseigner l'email est obligatoire")
    @Pattern(regexp = "^[a-zA-Z@ ._-]+$")
    @Column(unique=true)

    protected String email;

    @NotBlank(message="Renseigner le type de voie est obligatoire")
    @Pattern(regexp = "^[a-zA-Z -]+$")
    @JsonProperty("userRoadType")
    protected String userRoadType;

    @NotBlank(message="Renseigner l'adresse est obligatoire")
    @Pattern(regexp = "^[a-zA-Z -]+$")
    @JsonProperty("userAddress")
    protected String userAddress;

    @NotBlank(message="Renseigner le numéro d'adresse est obligatoire")
    @Pattern(regexp = "^[0-9a-zA-Z .-]+$")
    @JsonProperty("userAddressNumber")
    protected String userAddressNumber;

    @NotBlank(message="Renseigner le code postal est obligatoire")
    @Pattern(regexp = "^[0-9 .-]+$")
    @Size(min = 5)
    @JsonProperty("userZipCode")
    protected String userZipCode;

    @NotBlank(message="Renseigner le code postal est obligatoire")
    @Pattern(regexp = "^[a-zA-Z- ]+$")
    @JsonProperty("userCity")
    protected String userCity;

    @NotBlank(message="Renseigner un numéro de téléphone est obligatoire")
    @Pattern(regexp = "^[0-9a-zA-Z +-]+$")
    @Size(min = 10)
    @JsonProperty("userPhone")
    protected String userPhone;


    @NotBlank(message="Renseigner le mot de passe est obligatoire")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[#?!@$%^&*-]).{8,}$")
    @JsonProperty("userPassword")
    protected String userPassword;

    @DateTimeFormat
    @JsonProperty("userArrivalDate")
    protected LocalDate userArrivalDate;

    @DateTimeFormat
    @JsonProperty("userDepartureDate")
    protected LocalDate userDepartureDate;

    @JsonView(UtilisateurView.class)
    @NotBlank(message="Renseigner votre cursus est obligatoire")
    @Pattern(regexp = "^[0-9a-zA-Z _#-]+$")
    @JsonProperty("userCourse")
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
                getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserLastname(), getUserFirstname(), getEmail());
    }
}
