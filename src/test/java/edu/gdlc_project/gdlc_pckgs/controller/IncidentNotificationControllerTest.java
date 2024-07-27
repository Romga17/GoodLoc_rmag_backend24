package edu.gdlc_project.gdlc_pckgs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdlc_project.gdlc_pckgs.model.IncidentNotification;
import edu.gdlc_project.gdlc_pckgs.service.IncidentNotification_Service.IncidentNotificationServiceImp;
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
class IncidentNotificationControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private IncidentNotificationServiceImp declarationIncidentServiceImp;

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

        IncidentNotification testDeclaration= new IncidentNotification();

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
        IncidentNotification testSaveDeclaration= new IncidentNotification();

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