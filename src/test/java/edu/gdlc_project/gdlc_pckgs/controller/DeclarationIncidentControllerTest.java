package edu.gdlc_project.gdlc_pckgs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdlc_project.gdlc_pckgs.Goodloc24Application;
import edu.gdlc_project.gdlc_pckgs.model.DeclarationIncident;
import edu.gdlc_project.gdlc_pckgs.model.Utilisateur;
import edu.gdlc_project.gdlc_pckgs.service.Service_DeclarationIncident.DeclarationIncidentServiceImp;
import edu.gdlc_project.gdlc_pckgs.service.Service_DemandeReservation.DemandeReservationServiceImp;
import edu.gdlc_project.gdlc_pckgs.service.Service_Utilisateur.UtilisateurServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
class DeclarationIncidentControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private DeclarationIncidentServiceImp declarationIncidentServiceImp;

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

    @Test
    public void testReceptionAddIncident_devraitRetrournerHttpStatusOk() throws Exception {
        // Given:
        int idDeclarant = 1;
        int idMaterielIncident = 1;

        DeclarationIncident testDeclaration= new DeclarationIncident();

        String jsonRequestMatAdd = objectMapper.writeValueAsString(testDeclaration);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.post("/incident/ajouter/"+idMaterielIncident+"/"+idDeclarant)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestMatAdd))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionSaveIncident_devraitRetrournerHttpStatusOk() throws Exception {
        DeclarationIncident testSaveDeclaration= new DeclarationIncident();

        String jsonRequestSaveDecl = objectMapper.writeValueAsString(testSaveDeclaration);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.post("/incident/ajouter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestSaveDecl))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionGetAllTypeIncident_devraitRetrournerHttpStatusOk() throws Exception {
        mockMvc.perform(get("/incident/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionGetUserIncidents_devraitRetrournerHttpStatusOk() throws Exception {
        // Given:
        int idUtilisateurTestIncidents= 1;

        // When:
        mockMvc.perform(get("/incident/obtenir/"+idUtilisateurTestIncidents))
                // Then:
                .andExpect(status().isOk());
    }
}