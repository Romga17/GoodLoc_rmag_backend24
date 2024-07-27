package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.BookingRequest;
import edu.gdlc_project.gdlc_pckgs.repository.BookingRequestRepository;
import edu.gdlc_project.gdlc_pckgs.service.BookingRequest_Service.BookingRequestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reservation")
public class BookingRequestController {

    @Autowired
    protected BookingRequestServiceImp demandeReservationServiceImp;

    @Autowired
    protected BookingRequestRepository bookingRequestRepository;

    @GetMapping("/obtenir/liste")
    public List<BookingRequest> getAllDemandeReservation(){
        return demandeReservationServiceImp.getAllDemandeReservation();
    }

    @GetMapping("/obtenir/waitingList")
    public List<BookingRequest> getAllNotValid(){
        return demandeReservationServiceImp.getAllUnchecked();
    }

    @GetMapping("/obtenir/{id}")
    public List<BookingRequest> getUserAuthReservation(@PathVariable int id){

        return demandeReservationServiceImp.getUserReservations(id);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<BookingRequest> addRent(@RequestBody BookingRequest bookingRequest){
        return demandeReservationServiceImp.addUserReservation(bookingRequest);
    }

    @PutMapping("/validate")
    public ResponseEntity<BookingRequest> validateBookingRequest(@RequestBody BookingRequest bookingRequest){
        return demandeReservationServiceImp.validateRecord(bookingRequest.getId(), bookingRequest.isValidation());
    }

    @PutMapping("/deny/{id}")
    public ResponseEntity<BookingRequest> denyBookingRequest(@PathVariable int id, @RequestBody BookingRequest resaToDeny){
        return demandeReservationServiceImp.denyRecord(id, resaToDeny);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<BookingRequest> deleteReservationAsk(@PathVariable int id){
        return demandeReservationServiceImp.deleteBookingRequest(id);
    }
}
