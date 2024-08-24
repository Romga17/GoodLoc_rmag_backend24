package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.dto.BookingRequestDTO;
import edu.gdlc_project.gdlc_pckgs.model.BookingRequest;
import edu.gdlc_project.gdlc_pckgs.model.User;
import edu.gdlc_project.gdlc_pckgs.repository.BookingRequestRepository;
import edu.gdlc_project.gdlc_pckgs.repository.UserRepository;
import edu.gdlc_project.gdlc_pckgs.service.BookingRequest_Service.BookingRequestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = {"http://localhost:4200", "http://185.97.144.183:8082"})
@CrossOrigin(origins = "*")
@RequestMapping("/booking")
public class BookingRequestController {

    @Autowired
    protected BookingRequestServiceImp bookingRequestServiceImp;

    @Autowired
    protected BookingRequestRepository bookingRequestRepository;

    @Autowired
    protected UserRepository userRepository;

    @GetMapping("/list")
    public List<BookingRequest> getBookingLists(){
        return bookingRequestServiceImp.getAllBookingRequest();
    }

    @GetMapping("/get/unchecked")
    public List<BookingRequest> getAllNotValid(){
        return bookingRequestServiceImp.getAllUnchecked();
    }

    @GetMapping("/get/{id}")
    public List<BookingRequest> getUserBookings(@PathVariable int id){

        return bookingRequestServiceImp.getUserBookings(id);
    }

    @PostMapping("/add")
    public ResponseEntity<BookingRequest> addRent(@RequestBody BookingRequest bookingRequest){
        return bookingRequestServiceImp.addUserBooking(bookingRequest);
    }

    @PutMapping("/validate")
    public ResponseEntity<BookingRequest> validateBookingRequest(@RequestBody BookingRequestDTO bookingRequestDTO){
        // Convertir la chaîne de caractères en LocalDate
        LocalDate agreementDate = LocalDate.parse(bookingRequestDTO.getBookingRequestAgreementDate(), DateTimeFormatter.ISO_LOCAL_DATE);

        Optional<User> validatorOptional = userRepository.findById(bookingRequestDTO.getBookingRequestValidator().getId());
        if (!validatorOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // ou une autre réponse appropriée
        }
        User validator = validatorOptional.get();

        return bookingRequestServiceImp.validateRecord(
                bookingRequestDTO.getId(),
                bookingRequestDTO.isBookingRequestValid(),
                validator,
                agreementDate
        );
    }

    @PutMapping("/deny/{id}")
    public ResponseEntity<BookingRequest> denyBookingRequest(@PathVariable int id, @RequestBody BookingRequest bookingToDeny){
        return bookingRequestServiceImp.denyRecord(id, bookingToDeny);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookingRequest> deleteReservationAsk(@PathVariable int id){
        return bookingRequestServiceImp.deleteBookingRequest(id);
    }

    @GetMapping("/get/valid/{id}")
    public List<BookingRequest> getUserUncheckedBookings(@PathVariable int id){

        return bookingRequestServiceImp.getUserValidatedBookings(id);
    }

    @GetMapping("/get/updated")
    public List<BookingRequest> getBookingsListUpdated(){
        return bookingRequestServiceImp.getUpToDateBookings();
    }
}
