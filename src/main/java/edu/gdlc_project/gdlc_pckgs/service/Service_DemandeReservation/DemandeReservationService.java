package edu.gdlc_project.gdlc_pckgs.service.Service_DemandeReservation;

import edu.gdlc_project.gdlc_pckgs.model.DemandeReservation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DemandeReservationService {

    public DemandeReservation saveDemandeReservation(DemandeReservation demandeReservation);

    public List<DemandeReservation> getAllDemandeReservation();

    public List<DemandeReservation> getAllUnchecked();

    public ResponseEntity<DemandeReservation> addUserReservation(DemandeReservation demandeReservation);

    public List<DemandeReservation> getUserReservations(int id);

    public ResponseEntity<DemandeReservation> validateRecord(int idVal, boolean valid);

    public ResponseEntity<DemandeReservation> denyRecord(int idResa, DemandeReservation demandeReservationToDeny);

    public ResponseEntity<DemandeReservation> deleteBookingRequest(int idSup);

}

