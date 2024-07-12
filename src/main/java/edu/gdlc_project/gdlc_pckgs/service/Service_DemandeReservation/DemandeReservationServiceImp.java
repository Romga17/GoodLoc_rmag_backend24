package edu.gdlc_project.gdlc_pckgs.service.Service_DemandeReservation;

import edu.gdlc_project.gdlc_pckgs.model.*;
import edu.gdlc_project.gdlc_pckgs.repository.DemandeReservationRepository;
import edu.gdlc_project.gdlc_pckgs.repository.MaterielRepository;
import edu.gdlc_project.gdlc_pckgs.repository.UtilisateurRepository;
import edu.gdlc_project.gdlc_pckgs.service.EmailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeReservationServiceImp implements DemandeReservationService {

    @Autowired
    private DemandeReservationRepository demandeReservationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private MaterielRepository materielRepository;

    @Autowired
    private EmailServiceImp emailServiceImp;

    @Override
    public DemandeReservation saveDemandeReservation(DemandeReservation demandeReservation) {
        return demandeReservationRepository.save(demandeReservation);
    }

    @Override
    public List<DemandeReservation> getAllDemandeReservation() {
        return demandeReservationRepository.findAll();
    }

    @Override
    public ResponseEntity<DemandeReservation> addUserReservation(DemandeReservation resaMat) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(resaMat.getDemandeur().getId());
        Optional<Materiel> materielOptional = materielRepository.findById(resaMat.getMaterielReserv().getId());

        if (utilisateurOptional.isPresent() && materielOptional.isPresent()) {

            demandeReservationRepository.save(resaMat);

            return new ResponseEntity<>(resaMat, HttpStatus.OK);
        } else {
            throw new RuntimeException("Utilisateur ou Matériel non trouvé");
        }
    }

    @Override
    public List<DemandeReservation> getUserReservations(int id) {
        List<DemandeReservation> userBookings = demandeReservationRepository.findByDemandeurId((long)id);
        return userBookings;
    }

    @Override
    public ResponseEntity<DemandeReservation> validateRecord(int idVal, boolean valid) {
        Optional<DemandeReservation> demandeReservationOptionalVal = demandeReservationRepository.findById(idVal);

        if (demandeReservationOptionalVal.isPresent()) {
            DemandeReservation bookingRequestValidation = demandeReservationOptionalVal.get();
            bookingRequestValidation.setValidation(valid);
            demandeReservationRepository.save(bookingRequestValidation);

            //emailServiceImp.sendEmail(utilisateur.getEmail(), "Confirmation inscription", "Bonjour " + utilisateur.getEmail() + " et bienvenue chez Goodloc, vous n'êtes qu'à un clic de pouvoir réserver votre matériel.");
            emailServiceImp.sendEmail(bookingRequestValidation.getDemandeur().getEmail(), "Confirmation réservation", "Bonjour " + bookingRequestValidation.getDemandeur().getEmail() + " votre demande de réservation n° " + bookingRequestValidation.getId() + " a bien été validée. Vous recevrez très prochainement les informations pour récupérer le matériel.");
            return new ResponseEntity<>(bookingRequestValidation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<DemandeReservation> denyRecord(int idResa, DemandeReservation demandeReservationToDeny) {
        Optional<DemandeReservation> resaOptional = demandeReservationRepository.findById(idResa);

        System.out.println("test entrée méthode");
        if(resaOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        DemandeReservation reservation = resaOptional.get();
        reservation.setMotifRefus(demandeReservationToDeny.getMotifRefus());
        //Etait placé avant optional avant/ A corriger si erreur

        demandeReservationRepository.save(reservation);

        emailServiceImp.sendEmail(reservation.getDemandeur().getEmail(), "Information demande de réservation", "Bonjour " + reservation.getDemandeur().getEmail() + " votre demande de réservation n° " + reservation.getId()  + " a été refusée pour le motif suivant: " + reservation.getMotifRefus() );

        return new ResponseEntity<>(resaOptional.get(), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<DemandeReservation> deleteBookingRequest(int idSup) {
        Optional<DemandeReservation> demandeReservationOptional = demandeReservationRepository.findById(idSup);

        if(demandeReservationOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        demandeReservationRepository.deleteById(idSup);

        return new ResponseEntity<>(demandeReservationOptional.get(),HttpStatus.OK);
    }

    @Override
    public List<DemandeReservation> getAllUnchecked() {
        List<DemandeReservation> fullList = demandeReservationRepository.findAll();
        List<DemandeReservation> needValidationList = new ArrayList<>();

        for(DemandeReservation demandeReservation : fullList) {
            if(demandeReservation.isValidation()== false && demandeReservation.getMotifRefus()==null){
                needValidationList.add(demandeReservation);
            }
        }
        return needValidationList;
    }

}
