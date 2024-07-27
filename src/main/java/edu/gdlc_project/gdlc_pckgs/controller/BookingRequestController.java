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
@RequestMapping("/booking")
public class BookingRequestController {

    @Autowired
    protected BookingRequestServiceImp bookingRequestServiceImp;

    @Autowired
    protected BookingRequestRepository bookingRequestRepository;

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
    public ResponseEntity<BookingRequest> validateBookingRequest(@RequestBody BookingRequest bookingRequest){
        return bookingRequestServiceImp.validateRecord(bookingRequest.getId(), bookingRequest.isBookingRequestValid());
    }

    @PutMapping("/deny/{id}")
    public ResponseEntity<BookingRequest> denyBookingRequest(@PathVariable int id, @RequestBody BookingRequest bookingToDeny){
        return bookingRequestServiceImp.denyRecord(id, bookingToDeny);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookingRequest> deleteReservationAsk(@PathVariable int id){
        return bookingRequestServiceImp.deleteBookingRequest(id);
    }
}
