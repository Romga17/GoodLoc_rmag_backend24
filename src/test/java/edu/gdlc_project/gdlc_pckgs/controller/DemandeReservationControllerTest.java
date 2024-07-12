package edu.gdlc_project.gdlc_pckgs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdlc_project.gdlc_pckgs.Goodloc24Application;
import edu.gdlc_project.gdlc_pckgs.model.DemandeReservation;
import edu.gdlc_project.gdlc_pckgs.model.Utilisateur;
import edu.gdlc_project.gdlc_pckgs.repository.DemandeReservationRepository;
import edu.gdlc_project.gdlc_pckgs.service.Service_DemandeReservation.DemandeReservationServiceImp;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DemandeReservationControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private DemandeReservationServiceImp demandeReservationServiceImpTest;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DemandeReservationRepository demandeReservationRepositoryTest;

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
    public void testReceptionGetAllDemandeReservation_devraitRetrournerHttpStatusOk() throws Exception {
        //When:
        mockMvc.perform(get("/reservation/obtenir/liste"))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionGetAllNotValid_devraitRetrournerHttpStatusOk() throws Exception {
        //When:
        mockMvc.perform(get("/reservation/obtenir/waitingList"))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionGetUserAuthReservation_devraitRetrournerHttpStatusOk() throws Exception {
        // Given:
        int idDemandeReservationTest= 1;

        //When:
        mockMvc.perform(get("/reservation/obtenir/"+idDemandeReservationTest))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionAddRent_devraitRetrournerHttpStatusOk() throws Exception {
        // Given:
        DemandeReservation testDemandeReservation = new DemandeReservation();

        String jsonRequestBookingAdd = objectMapper.writeValueAsString(testDemandeReservation);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.post("/reservation/ajouter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBookingAdd))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionvalidateBookingRequest_devraitRetrournerHttpStatusOk() throws Exception {
        // Given:
        DemandeReservation testDemandeReservationToModify = new DemandeReservation();

        String jsonRequestBookingValidation = objectMapper.writeValueAsString(testDemandeReservationToModify);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.put("/reservation/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBookingValidation))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionDenyBookingRequest_devraitRetrournerHttpStatusOk() throws Exception {
        // Given:
        int idValidateur = 17;

        DemandeReservation testDemandeReservationToDeny = new DemandeReservation();

        String jsonRequestBookingDeny = objectMapper.writeValueAsString(testDemandeReservationToDeny);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.put("/reservation/deny/"+idValidateur)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBookingDeny))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    void testReceptionDeleteReservationAsk_devraitRetrournerHttpStatusOk() throws Exception {
        // Given:
        int idBookingToDelete = 1;

        // When:
        mockMvc.perform(MockMvcRequestBuilders.delete("/reservation/supprimer/"+idBookingToDelete))
                // Then:
                .andExpect(status().isOk());
    }
}