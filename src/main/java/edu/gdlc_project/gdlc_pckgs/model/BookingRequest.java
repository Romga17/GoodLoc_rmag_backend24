package edu.gdlc_project.gdlc_pckgs.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@Entity
@Table(name = "booking_request")
public class BookingRequest {

    public BookingRequest() {
        this.bookingRequestValidation = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(User.class)
    protected int id;

    protected boolean bookingRequestValidation;

    protected Date bookingRequestStartDate;

    protected Date bookingRequestEndDate;

    @Nullable
    protected String bookingRequestdenialReason;

    protected Date bookingRequestDate;

    @Column(columnDefinition="TEXT")
    @Nullable
    protected String bookingRequestUserMessage;

    @Nullable
    protected Date bookingRequestAgreementDate;

    @ManyToOne
    protected User bookingRequestRequester;

    @ManyToOne
    @Nullable
    protected User bookingRequestValidator;

    @ManyToOne
    protected Material bookingObjectMaterial;

    @Override
    public String toString() {
        return "DemandeReservation{" +
                "dateAccord=" + bookingRequestAgreementDate +
                ", dateDebut=" + bookingRequestStartDate +
                ", dateDemande=" + bookingRequestDate +
                ", dateFin=" + bookingRequestEndDate +
                ", demandeur=" + bookingRequestRequester +
                ", id=" + id +
                ", materiel=" + bookingObjectMaterial +
                ", messageUser='" + bookingRequestUserMessage + '\'' +
                ", motifRefus='" + bookingRequestdenialReason + '\'' +
                ", validateur=" + bookingRequestValidator +
                ", validation=" + bookingRequestValidation +
                '}';
    }
}
