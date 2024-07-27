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
    public BookingRequest saveDemandeReservation(BookingRequest bookingRequest) {
        return bookingRequestRepository.save(bookingRequest);
    }

    @Override
    public List<BookingRequest> getAllDemandeReservation() {
        return bookingRequestRepository.findAll();
    }

    @Override
    public ResponseEntity<BookingRequest> addUserReservation(BookingRequest resaMat) {
        Optional<User> utilisateurOptional = userRepository.findById(resaMat.getDemandeur().getId());
        Optional<Material> materielOptional = materialRepository.findById(resaMat.getMaterialReserv().getId());

        if (utilisateurOptional.isPresent() && materielOptional.isPresent()) {

            bookingRequestRepository.save(resaMat);

            return new ResponseEntity<>(resaMat, HttpStatus.OK);
        } else {
            throw new RuntimeException("Utilisateur ou Matériel non trouvé");
        }
    }

    @Override
    public List<BookingRequest> getUserReservations(int id) {
        List<BookingRequest> userBookings = bookingRequestRepository.findByDemandeurId((long)id);
        return userBookings;
    }

    @Override
    public ResponseEntity<BookingRequest> validateRecord(int idVal, boolean valid) {
        Optional<BookingRequest> demandeReservationOptionalVal = bookingRequestRepository.findById(idVal);

        if (demandeReservationOptionalVal.isPresent()) {
            BookingRequest bookingRequestValidation = demandeReservationOptionalVal.get();
            bookingRequestValidation.setValidation(valid);
            bookingRequestRepository.save(bookingRequestValidation);

            //emailServiceImp.sendEmail(utilisateur.getEmail(), "Confirmation inscription", "Bonjour " + utilisateur.getEmail() + " et bienvenue chez Goodloc, vous n'êtes qu'à un clic de pouvoir réserver votre matériel.");
            emailServiceImp.sendEmail(bookingRequestValidation.getDemandeur().getEmail(), "Confirmation réservation", "Bonjour " + bookingRequestValidation.getDemandeur().getEmail() + " votre demande de réservation n° " + bookingRequestValidation.getId() + " a bien été validée. Vous recevrez très prochainement les informations pour récupérer le matériel.");
            return new ResponseEntity<>(bookingRequestValidation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<BookingRequest> denyRecord(int idResa, BookingRequest bookingRequestToDeny) {
        Optional<BookingRequest> resaOptional = bookingRequestRepository.findById(idResa);

        System.out.println("test entrée méthode");
        if(resaOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BookingRequest reservation = resaOptional.get();
        reservation.setMotifRefus(bookingRequestToDeny.getMotifRefus());
        //Etait placé avant optional avant/ A corriger si erreur

        bookingRequestRepository.save(reservation);

        emailServiceImp.sendEmail(reservation.getDemandeur().getEmail(), "Information demande de réservation", "Bonjour " + reservation.getDemandeur().getEmail() + " votre demande de réservation n° " + reservation.getId()  + " a été refusée pour le motif suivant: " + reservation.getMotifRefus() );

        return new ResponseEntity<>(resaOptional.get(), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<BookingRequest> deleteBookingRequest(int idSup) {
        Optional<BookingRequest> demandeReservationOptional = bookingRequestRepository.findById(idSup);

        if(demandeReservationOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bookingRequestRepository.deleteById(idSup);

        return new ResponseEntity<>(demandeReservationOptional.get(),HttpStatus.OK);
    }

    @Override
    public List<BookingRequest> getAllUnchecked() {
        List<BookingRequest> fullList = bookingRequestRepository.findAll();
        List<BookingRequest> needValidationList = new ArrayList<>();

        for(BookingRequest bookingRequest : fullList) {
            if(bookingRequest.isValidation()== false && bookingRequest.getMotifRefus()==null){
                needValidationList.add(bookingRequest);
            }
        }
        return needValidationList;
    }

}
