package edu.gdlc_project.gdlc_pckgs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdlc_project.gdlc_pckgs.Goodloc24Application;
import edu.gdlc_project.gdlc_pckgs.model.Materiel;
import edu.gdlc_project.gdlc_pckgs.model.Utilisateur;
import edu.gdlc_project.gdlc_pckgs.repository.MaterielRepository;
import edu.gdlc_project.gdlc_pckgs.repository.UtilisateurRepository;
import edu.gdlc_project.gdlc_pckgs.security.JwtUtils;
import edu.gdlc_project.gdlc_pckgs.service.Service_Materiel.MaterielServiceImp;
import edu.gdlc_project.gdlc_pckgs.service.Service_Utilisateur.UtilisateurServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MaterielControllerTest {

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

    //Tests de la bonne réception des endpoints de MaterielController:
    @Test
    public void testReceptionListeMaterielController_devraitRetrournerHttpStatusOk() throws Exception {
        mockMvc.perform(get("/materiel/obtenir/liste"))
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionRecupererUnMaterielController_devraitRetrournerHttpStatusOk() throws Exception {
        int idMaterielTest = 1;

        mockMvc.perform(get("/materiel/" + idMaterielTest))
                .andExpect(status().isOk());
    }

    @Test
    public void testReceptionAjoutMateriel_devraitRetrournerHttpStatusOk() throws Exception {
        // Given:
        Materiel materielTest = new Materiel();

        String jsonRequestMatAdd = objectMapper.writeValueAsString(materielTest);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.post("/materiel/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestMatAdd))
                .andExpect(status().isOk());

        /* Then:*/
    }

    @Test
    void supprimerMateriel() throws Exception {
        // Given:
        int idMatToDel = 100;

        // When:
        mockMvc.perform(MockMvcRequestBuilders.delete("/materiel/supprimer/"+idMatToDel))
                //Then:
                .andExpect(status().isOk());
    }

    /*@Test
    void supprimerMateriel() throws Exception {
        // Given:
        Materiel materielTest = new Materiel();
        materielTest.setId(99999);
        materielTest.setDescription("Ceci est mon matériel test");
        materielTest.setReference("REFTESTMAT01");
        materielTest.setStatut("Disponible");
        materielTest.setPrixAchat(0.99f);

        int idMatToDel = materielTest.getId();

        String jsonRequestMatAdd = objectMapper.writeValueAsString(materielTest);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.post("/materiel/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestMatAdd));

        Then:
        mockMvc.perform(MockMvcRequestBuilders.delete("/materiel/supprimer/"+idMatToDel)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestMatAdd))
                .andExpect(status().isOk());
    }*/

        /*@Test
    void obtenir() {
        // Given:
        Materiel materielTest = new Materiel();
        materielTest.setDescription("Ceci est mon matériel test");
        materielTest.setReference("REFTESTMAT01");
        materielTest.setStatut("Disponible");
        materielTest.setPrixAchat(0.99f);


         Mock du repository pour sauvegarder l'utilisateur
        when(materielRepositoryTest.save(any(Utilisateur.class))).thenReturn(testUtilisateur);
        // Mock du repository pour retrouver l'utilisateur par email
        when(utilisateurRepositoryTest.findByEmail("robert.mrtest@gmail.com")).thenReturn(Optional.of(testUtilisateur));

        String jsonRequestMatAdd = objectMapper.writeValueAsString(materielTest);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.post("/materiel/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestMatAdd))
                .andExpect(status().isOk());

        /* Then:
        Utilisateur retrievedUtilisateur = utilisateurRepositoryTest.findByEmail("robert.mrtest@gmail.com").get();
        Assertions.assertEquals("robert.mrtest@gmail.com", retrievedUtilisateur.getEmail());
    }*/
}