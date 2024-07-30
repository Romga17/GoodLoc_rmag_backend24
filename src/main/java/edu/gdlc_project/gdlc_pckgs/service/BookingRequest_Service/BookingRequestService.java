package edu.gdlc_project.gdlc_pckgs.service.BookingRequest_Service;

import edu.gdlc_project.gdlc_pckgs.model.BookingRequest;
import edu.gdlc_project.gdlc_pckgs.model.User;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface BookingRequestService {

    public BookingRequest saveBookingRequest(BookingRequest bookingRequest);

    public List<BookingRequest> getAllBookingRequest();

    public List<BookingRequest> getAllUnchecked();

    public ResponseEntity<BookingRequest> addUserBooking(BookingRequest bookingRequest);

    public List<BookingRequest> getUserBookings(int id);

    public ResponseEntity<BookingRequest> validateRecord(int idVal, boolean valid, User validator, LocalDate agreementDate);

    public ResponseEntity<BookingRequest> denyRecord(int idResa, BookingRequest bookingRequestToDeny);

    public ResponseEntity<BookingRequest> deleteBookingRequest(int idSup);

    public List<BookingRequest> getUserValidatedBookings(int id);

}

