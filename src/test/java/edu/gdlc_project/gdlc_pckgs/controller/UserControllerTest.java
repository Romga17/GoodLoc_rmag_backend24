package edu.gdlc_project.gdlc_pckgs.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdlc_project.gdlc_pckgs.exception.NotFoundException;
import edu.gdlc_project.gdlc_pckgs.model.User;
import edu.gdlc_project.gdlc_pckgs.repository.UserRepository;
import edu.gdlc_project.gdlc_pckgs.service.User_Service.UserServiceImp;
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


import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserServiceImp utilisateurServiceImpTest;

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

    //Tests de la bonne réception des requêtes de la part des endpoints de UtilisateurController:

    @Test
    public void testReceptionGetSingleUser_shouldReturnHttpStatusOk() throws Exception {
        // Given:
        int idUtilisateurTest= 1;

        // When:
        mockMvc.perform(get("/user/get/"+idUtilisateurTest))
                // Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionUserList_shouldReturnHttpStatusOk() throws Exception {
        // When:
        mockMvc.perform(get("/user/list"))
                // Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionModifyUser_shouldReturnHttpStatusOk() throws Exception {
        // Given:
        User testUserModify = new User();

        int idTestUtilisateurToModify = 1;

        String jsonRequest = objectMapper.writeValueAsString(testUserModify);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.put("/user/modify/"+idTestUtilisateurToModify)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                // Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionAddUser_shouldReturnHttpStatusOk() throws Exception {
        // Given:
        User testUserAdd = new User();

        String jsonRequestMatAdd = objectMapper.writeValueAsString(testUserAdd);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestMatAdd))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    void testReceptionDeleteUser_shouldReturnHttpStatusOk() throws Exception {
        // Given:
        int idUserToDelete = 34;

        // When:
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/"+idUserToDelete))
                // Then:
                .andExpect(status().isOk());

    }

    @Test
    public void retrouverUtilisateurInexistant_shouldReturn404() throws Exception {
        // Given
        int idNonExistingUser = 999;

        // Stubbing du service pour lancer une exception
        when(utilisateurServiceImpTest.getUserById(idNonExistingUser)).thenThrow(new NotFoundException("Utilisateur non trouvé avec l'ID : " + idNonExistingUser));

        // When
        mockMvc.perform(get("/user/get/{id}", idNonExistingUser)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());  // Attend un statut HTTP 404 Not Found
    }
}
