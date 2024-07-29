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
        this.bookingRequestValid= false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(User.class)
    @Column(name = "id_booking_request")
    protected int id;

    protected boolean bookingRequestValid;

    protected Date bookingRequestStartDate;

    protected Date bookingRequestEndDate;

    @Nullable
    protected String bookingRequestDenialReason;

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
        return "Booking Request{" +
                "Agreement date=" + bookingRequestAgreementDate +
                ", Start date=" + bookingRequestStartDate +
                ", Request date=" + bookingRequestDate +
                ", End date=" + bookingRequestEndDate +
                ", Requester=" + bookingRequestRequester +
                ", id=" + id +
                ", materiel=" + bookingObjectMaterial +
                ", user message='" + bookingRequestUserMessage + '\'' +
                ", denial reason='" + bookingRequestDenialReason + '\'' +
                ", validator=" + bookingRequestValidator +
                ", Is valid =" + bookingRequestValid +
                '}';
    }
}
