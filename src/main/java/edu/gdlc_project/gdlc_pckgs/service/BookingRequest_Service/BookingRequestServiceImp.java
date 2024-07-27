package edu.gdlc_project.gdlc_pckgs.service.BookingRequest_Service;

import edu.gdlc_project.gdlc_pckgs.model.*;
import edu.gdlc_project.gdlc_pckgs.repository.BookingRequestRepository;
import edu.gdlc_project.gdlc_pckgs.repository.MaterialRepository;
import edu.gdlc_project.gdlc_pckgs.repository.UserRepository;
import edu.gdlc_project.gdlc_pckgs.service.EmailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingRequestServiceImp implements BookingRequestService {

    @Autowired
    private BookingRequestRepository bookingRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private EmailServiceImp emailServiceImp;

    @Override
    public BookingRequest saveBookingRequest(BookingRequest bookingRequest) {
        return bookingRequestRepository.save(bookingRequest);
    }

    @Override
    public List<BookingRequest> getAllBookingRequest() {
        return bookingRequestRepository.findAll();
    }

    @Override
    public ResponseEntity<BookingRequest> addUserBooking(BookingRequest materialToBook) {
        Optional<User> userOptional = userRepository.findById(materialToBook.getBookingRequestRequester().getId());
        Optional<Material> materialOptional = materialRepository.findById(materialToBook.getBookingObjectMaterial().getId());

        if (userOptional.isPresent() && materialOptional.isPresent()) {

            bookingRequestRepository.save(materialToBook);

            return new ResponseEntity<>(materialToBook, HttpStatus.OK);
        } else {
            throw new RuntimeException("Utilisateur ou Matériel non trouvé");
        }
    }

    @Override
    public List<BookingRequest> getUserBookings(int id) {
        List<BookingRequest> userBookings = bookingRequestRepository.findByBookingRequestRequesterId((long)id);
        return userBookings;
    }

    @Override
    public ResponseEntity<BookingRequest> validateRecord(int idValue, boolean valid) {
        Optional<BookingRequest> bookingRequestOptionalValue = bookingRequestRepository.findById(idValue);

        if (bookingRequestOptionalValue.isPresent()) {
            BookingRequest bookingRequestToAgree = bookingRequestOptionalValue.get();
            bookingRequestToAgree.setBookingRequestValid(valid);
            bookingRequestRepository.save(bookingRequestToAgree);

            //emailServiceImp.sendEmail(user.getUserEmail(), "Confirmation inscription", "Bonjour " + user.getUserEmail() + " et bienvenue chez Goodloc, vous n'êtes qu'à un clic de pouvoir réserver votre matériel.");
            emailServiceImp.sendEmail(bookingRequestToAgree.getBookingRequestRequester().getUserEmail(), "Confirmation réservation", "Bonjour " + bookingRequestToAgree.getBookingRequestRequester().getUserEmail() + " votre demande de réservation n° " + bookingRequestToAgree.getId() + " a bien été validée. Vous recevrez très prochainement les informations pour récupérer le matériel.");
            return new ResponseEntity<>(bookingRequestToAgree, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<BookingRequest> denyRecord(int bookingId, BookingRequest bookingRequestToDeny) {
        Optional<BookingRequest> resaOptional = bookingRequestRepository.findById(bookingId);

        System.out.println("test entrée méthode");
        if(resaOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BookingRequest reservation = resaOptional.get();
        reservation.setBookingRequestdenialReason(bookingRequestToDeny.getBookingRequestdenialReason());
        //Etait placé avant optional avant/ A corriger si erreur

        bookingRequestRepository.save(reservation);

        emailServiceImp.sendEmail(reservation.getBookingRequestRequester().getUserEmail(), "Information demande de réservation", "Bonjour " + reservation.getBookingRequestRequester().getUserEmail() + " votre demande de réservation n° " + reservation.getId()  + " a été refusée pour le motif suivant: " + reservation.getBookingRequestdenialReason() );

        return new ResponseEntity<>(resaOptional.get(), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<BookingRequest> deleteBookingRequest(int idSup) {
        Optional<BookingRequest> bookingRequestOptional = bookingRequestRepository.findById(idSup);

        if(bookingRequestOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bookingRequestRepository.deleteById(idSup);

        return new ResponseEntity<>(bookingRequestOptional.get(),HttpStatus.OK);
    }

    @Override
    public List<BookingRequest> getAllUnchecked() {
        List<BookingRequest> fullList = bookingRequestRepository.findAll();
        List<BookingRequest> bookingRequestToCheckList = new ArrayList<>();

        for(BookingRequest bookingRequest : fullList) {
            if(bookingRequest.isBookingRequestValid()== false && bookingRequest.getBookingRequestdenialReason()==null){
                bookingRequestToCheckList.add(bookingRequest);
            }
        }
        return bookingRequestToCheckList;
    }

}
