package edu.gdlc_project.gdlc_pckgs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdlc_project.gdlc_pckgs.model.User;
import edu.gdlc_project.gdlc_pckgs.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AuthenticationControllerTest {


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    //@Autowired modifié pour mockbean
    @MockBean
    UserRepository userRepositoryTest;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    //OK
    @Test
    public void registerTest_shouldReceiveNewUserCreated() throws Exception {

        // Given:
        User testUser = new User();
        testUser.setId(98);
        testUser.setUserFirstname("MrTest");
        testUser.setUserLastname("Robert");
        testUser.setEmail("robert.mrtest@gmail.com");
        testUser.setUserAddress("du test");
        testUser.setUserAddressNumber("100");
        testUser.setUserRoadType("rue");
        testUser.setUserCity("Testville");
        testUser.setUserZipCode("57860");
        testUser.setUserCourse("CDA Java");
        testUser.setUserPhone("+336744552");
        testUser.setUserPassword("rooT17goodloc!");
        // autres setters...

        String jsonRequest = objectMapper.writeValueAsString(testUser);

        // Mock the save operation
        when(userRepositoryTest.save(any(User.class))).thenReturn(testUser);
        // Mock the findByEmail operation
        when(userRepositoryTest.findByEmail("robert.mrtest@gmail.com")).thenReturn(Optional.of(testUser));

        // When:
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());

        // Then:
        Optional<User> userOptional = userRepositoryTest.findByEmail("robert.mrtest@gmail.com");
        Assertions.assertTrue(userOptional.isPresent(), "User not found in the database");

        User retrievedUser = userOptional.orElseThrow(() -> new AssertionError("User not found"));
        Assertions.assertEquals("robert.mrtest@gmail.com", retrievedUser.getEmail());
    }


    /*@Test
    public void testInscription_doitRetrouverUtilisateur() throws Exception {
        // Given:
        Utilisateur testUtilisateur = new Utilisateur();
        testUtilisateur.setId(98);
        testUtilisateur.setNom("MrTest");
        testUtilisateur.setPrenom("Robert");
        testUtilisateur.setEmail("robert.mrtest@gmail.com");
        testUtilisateur.setAdresse("du test");
        testUtilisateur.setNumeroAdresse("100");
        testUtilisateur.setTypeVoie("rue");
        testUtilisateur.setVille("Testville");
        testUtilisateur.setCodePostal("57860");
        testUtilisateur.setCursus("CDA Java");
        testUtilisateur.setTelephone("+336744552");
        testUtilisateur.setMotDePasse("rooT17goodloc!");

        // Mock du repository pour sauvegarder l'utilisateur
        when(utilisateurRepositoryTest.save(any(Utilisateur.class))).thenReturn(testUtilisateur);
        // Mock du repository pour retrouver l'utilisateur par email
        when(utilisateurRepositoryTest.findByEmail("robert.mrtest@gmail.com")).thenReturn(Optional.of(testUtilisateur));

        String jsonRequest = objectMapper.writeValueAsString(testUtilisateur);

        // When:
        mockMvc.perform(MockMvcRequestBuilders.post("/inscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());

        // Then:
        Utilisateur retrievedUtilisateur = utilisateurRepositoryTest.findByEmail("robert.mrtest@gmail.com").get();
        Assertions.assertEquals("robert.mrtest@gmail.com", retrievedUtilisateur.getEmail());
    }



    @Test
    public void testConnexion_doitRetournerJwt() throws Exception {
        // Given:
        Utilisateur testUtilisateurConnexion = new Utilisateur();
        testUtilisateurConnexion.setEmail("nielsen.leo@institute.com");
        testUtilisateurConnexion.setMotDePasse("root17!Goodloc");

        String jsonRequestConnexion = objectMapper.writeValueAsString(testUtilisateurConnexion);

        UserDetails userDetailsTest = new User(testUtilisateurConnexion.getEmail(), testUtilisateurConnexion.getMotDePasse(), Collections.emptyList());
        String jwtToken = "fake-jwt-token";

        // Mocking the behavior of the dependencies
        when(authenticationProviderTest.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(userDetailsTest, testUtilisateurConnexion.getMotDePasse(), Collections.emptyList()));

        when(jwtUtils.generateToken(userDetailsTest)).thenReturn(jwtToken);

        // When:
        MvcResult result = mockMvc.perform(post("/connexion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestConnexion))
                .andExpect(status().isOk())
                .andReturn();

        // Log the response body for debugging
        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Response Body: " + responseBody);

        // Then:
        Assertions.assertFalse(responseBody.isEmpty(), "The response body should not be empty.");

        Map<String, Object> expectedResponse = Map.of("jwt", jwtToken);
        Map<String, Object> actualResponse = objectMapper.readValue(responseBody, new com.fasterxml.jackson.core.type.TypeReference<>() {});

        Assertions.assertEquals(expectedResponse, actualResponse);
    }*/
}
