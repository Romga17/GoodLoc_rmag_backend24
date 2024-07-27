package edu.gdlc_project.gdlc_pckgs.service.BookingRequest_Service;

import edu.gdlc_project.gdlc_pckgs.model.BookingRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingRequestService {

    public BookingRequest saveDemandeReservation(BookingRequest bookingRequest);

    public List<BookingRequest> getAllDemandeReservation();

    public List<BookingRequest> getAllUnchecked();

    public ResponseEntity<BookingRequest> addUserReservation(BookingRequest bookingRequest);

    public List<BookingRequest> getUserReservations(int id);

    public ResponseEntity<BookingRequest> validateRecord(int idVal, boolean valid);

    public ResponseEntity<BookingRequest> denyRecord(int idResa, BookingRequest bookingRequestToDeny);

    public ResponseEntity<BookingRequest> deleteBookingRequest(int idSup);

}

