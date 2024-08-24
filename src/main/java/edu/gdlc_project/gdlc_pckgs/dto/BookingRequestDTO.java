package edu.gdlc_project.gdlc_pckgs.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequestDTO {
    private int id;
    private boolean bookingRequestValid;
    private String bookingRequestAgreementDate; // Utilisé pour recevoir la date au format String
    private UserDTO bookingRequestValidator; // Utilisé pour recevoir l'objet User

    }
