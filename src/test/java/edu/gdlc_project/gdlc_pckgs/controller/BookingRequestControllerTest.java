package edu.gdlc_project.gdlc_pckgs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdlc_project.gdlc_pckgs.model.BookingRequest;
import edu.gdlc_project.gdlc_pckgs.repository.BookingRequestRepository;
import edu.gdlc_project.gdlc_pckgs.service.BookingRequest_Service.BookingRequestServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BookingRequestControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    //Tests de la bonne réception des endpoints de DemandeReservationController:

    @Test
    public void testGetAllBookings_shouldReturnHttpStatusOk() throws Exception {
        //When:
        mockMvc.perform(get("/booking/list"))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionGetAllNotValid_shouldReturnHttpStatusOk() throws Exception {
        //When:
        mockMvc.perform(get("/booking/get/unchecked"))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionGetUserBookings_shouldReturnHttpStatusOk() throws Exception {
        // Given:
        int idBookingRequestTest= 1;

        //When:
        mockMvc.perform(get("/booking/get/"+idBookingRequestTest))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionAddBooking_shouldReturnHttpStatusOk() throws Exception {
        // Given:
        BookingRequest testBookingRequest = new BookingRequest();

        String jsonRequestBookingAdd = objectMapper.writeValueAsString(testBookingRequest);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.post("/booking/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBookingAdd))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionBookingRequestValidation_shouldReturnHttpStatusOk() throws Exception {
        // Given:
        BookingRequest testBookingRequestToModify = new BookingRequest();

        String jsonRequestBookingValidation = objectMapper.writeValueAsString(testBookingRequestToModify);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.put("/booking/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBookingValidation))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionDenyBookingRequest_shouldReturnHttpStatusOk() throws Exception {
        // Given:
        int bookingRequestValidatorId = 17;

        BookingRequest testBookingRequestToDeny = new BookingRequest();

        String jsonRequestBookingDeny = objectMapper.writeValueAsString(testBookingRequestToDeny);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.put("/booking/deny/"+ bookingRequestValidatorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBookingDeny))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    void testReceptionDeleteReservationAsk_devraitRetrournerHttpStatusOk() throws Exception {
        // Given:
        int BookingToDeleteId = 1;

        // When:
        mockMvc.perform(MockMvcRequestBuilders.delete("/booking/delete/"+ BookingToDeleteId))
                // Then:
                .andExpect(status().isOk());
    }
}