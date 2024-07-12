package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.DemandeReservation;
import edu.gdlc_project.gdlc_pckgs.repository.DemandeReservationRepository;
import edu.gdlc_project.gdlc_pckgs.service.Service_DemandeReservation.DemandeReservationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reservation")
public class DemandeReservationController {

    @Autowired
    protected DemandeReservationServiceImp demandeReservationServiceImp;

    @Autowired
    protected DemandeReservationRepository demandeReservationRepository;

    @GetMapping("/obtenir/liste")
    public List<DemandeReservation> getAllDemandeReservation(){
        return demandeReservationServiceImp.getAllDemandeReservation();
    }

    @GetMapping("/obtenir/waitingList")
    public List<DemandeReservation> getAllNotValid(){
        return demandeReservationServiceImp.getAllUnchecked();
    }

    @GetMapping("/obtenir/{id}")
    public List<DemandeReservation> getUserAuthReservation(@PathVariable int id){

        return demandeReservationServiceImp.getUserReservations(id);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<DemandeReservation> addRent(@RequestBody DemandeReservation demandeReservation){
        return demandeReservationServiceImp.addUserReservation(demandeReservation);
    }

    @PutMapping("/validate")
    public ResponseEntity<DemandeReservation> validateBookingRequest(@RequestBody DemandeReservation demandeReservation){
        return demandeReservationServiceImp.validateRecord(demandeReservation.getId(), demandeReservation.isValidation());
    }

    @PutMapping("/deny/{id}")
    public ResponseEntity<DemandeReservation> denyBookingRequest(@PathVariable int id,@RequestBody DemandeReservation resaToDeny){
        return demandeReservationServiceImp.denyRecord(id, resaToDeny);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<DemandeReservation> deleteReservationAsk(@PathVariable int id){
        return demandeReservationServiceImp.deleteBookingRequest(id);
    }
}
