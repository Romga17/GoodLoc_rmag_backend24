package edu.gdlc_project.gdlc_pckgs.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdlc_project.gdlc_pckgs.Goodloc24Application;
import edu.gdlc_project.gdlc_pckgs.exception.NotFoundException;
import edu.gdlc_project.gdlc_pckgs.model.Utilisateur;
import edu.gdlc_project.gdlc_pckgs.repository.UtilisateurRepository;
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


import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UtilisateurControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UtilisateurServiceImp utilisateurServiceImpTest;

    @MockBean
    private UtilisateurRepository utilisateurRepositoryTest;

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
    public void testReceptionGetSingleUser_devraitRetrournerHttpStatusOk() throws Exception {
        // Given:
        int idUtilisateurTest= 1;

        // When:
        mockMvc.perform(get("/utilisateur/obtenir/"+idUtilisateurTest))
                // Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionUserList_devraitRetrournerHttpStatusOk() throws Exception {
        // When:
        mockMvc.perform(get("/utilisateur/obtenir/liste"))
                // Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionModifyUser_devraitRetrournerHttpStatusOk() throws Exception {
        // Given:
        Utilisateur testUtilisateurModify = new Utilisateur();

        int idTestUtilisateurToModify = 1;

        String jsonRequest = objectMapper.writeValueAsString(testUtilisateurModify);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.put("/utilisateur/modifier/"+idTestUtilisateurToModify)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                // Then:
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionAddUser_devraitRetrournerHttpStatusOk() throws Exception {
        // Given:
        Utilisateur testUtilisateurAdd = new Utilisateur();

        String jsonRequestMatAdd = objectMapper.writeValueAsString(testUtilisateurAdd);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.post("/utilisateur/ajouter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestMatAdd))
                //Then:
                .andExpect(status().isOk());
    }

    @Test
    void testReceptionDeleteUser_devraitRetrournerHttpStatusOk() throws Exception {
        // Given:
        int idUserToDel = 7;

        // When:
        mockMvc.perform(MockMvcRequestBuilders.delete("/utilisateur/supprimer/"+idUserToDel))
                // Then:
                .andExpect(status().isOk());

    }

    @Test
    public void retrouverUtilisateurInexistant_devraitRetourner404() throws Exception {
        // Given
        int idUtilisateurInexistant = 999;

        // Stubbing du service pour lancer une exception
        when(utilisateurServiceImpTest.getUserById(idUtilisateurInexistant)).thenThrow(new NotFoundException("Utilisateur non trouvé avec l'ID : " + idUtilisateurInexistant));

        // When
        mockMvc.perform(get("/utilisateurs/obtenir/{id}", idUtilisateurInexistant)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());  // Attend un statut HTTP 404 Not Found
    }
}
