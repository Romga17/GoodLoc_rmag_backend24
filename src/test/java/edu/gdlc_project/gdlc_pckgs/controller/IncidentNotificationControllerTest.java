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
    public void testReceptionAddIncident_shouldReturnHttpStatusOk() throws Exception {
        // Given:
        int notificatorId = 1;
        int notificatedMaterialId = 1;

        IncidentNotification testDeclaration= new IncidentNotification();

        String jsonRequestMatAdd = objectMapper.writeValueAsString(testDeclaration);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.post("/incident/add/"+notificatedMaterialId+"/"+notificatorId)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestMatAdd))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionSaveIncident_shouldReturnHttpStatusOk() throws Exception {
        IncidentNotification testSaveNotification= new IncidentNotification();

        String jsonRequestSaveDecl = objectMapper.writeValueAsString(testSaveNotification);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.post("/incident/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestSaveDecl))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionGetAllTypesOfIncident_shouldReturnHttpStatusOk() throws Exception {
        mockMvc.perform(get("/incident/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionGetUserIncidents_shouldReturnHttpStatusOk() throws Exception {
        // Given:
        int idUserTest= 1;

        // When:
        mockMvc.perform(get("/incident/get/"+idUserTest))
                // Then:
                .andExpect(status().isOk());
    }
}