package edu.gdlc_project.gdlc_pckgs.service.User_Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdlc_project.gdlc_pckgs.model.Role;
import edu.gdlc_project.gdlc_pckgs.model.User;
import edu.gdlc_project.gdlc_pckgs.repository.RoleRepository;
import edu.gdlc_project.gdlc_pckgs.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserServiceImpTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserServiceImp utilisateurServiceImpTest;

    @MockBean
    private UserRepository userRepositoryTest;

    @MockBean
    private RoleRepository roleRepositoryTest;

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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetourSaveUtilisateur_doitRetournerUnUtilisateur() throws Exception {
        // Given
        User newUser = new User();
        newUser.setNom("MrTest");
        newUser.setPrenom("Robert");
        newUser.setEmail("robert.mrtest@gmail.com");
        newUser.setAdresse("du test");
        newUser.setNumeroAdresse("100");
        newUser.setTypeVoie("rue");
        newUser.setVille("Testville");
        newUser.setCodePostal("57860");
        newUser.setCursus("CDA Java");
        newUser.setTelephone("+336744552");
        newUser.setMotDePasse("rooT17goodloc!");

        // When
        when(userRepositoryTest.save(newUser)).thenReturn(newUser);
        ResponseEntity<User> savedUtilisateur = utilisateurServiceImpTest.saveUtilisateur(newUser);

        // Then
        assertEquals(newUser, savedUtilisateur.getBody());
        verify(userRepositoryTest, times(1)).save(newUser);
    }

    @Test
    public void testRetourgetAllUsers_doitRetournerListeUtilisateurs() throws Exception {
        // Given:
        List<User> utilisateursListTest = new ArrayList<>();

        User newUser = new User();
        newUser.setNom("MrTest");
        newUser.setPrenom("Robert");
        /*newUtilisateur.setEmail("robert.mrtest@gmail.com");
        newUtilisateur.setAdresse("du test");
        newUtilisateur.setNumeroAdresse("100");
        newUtilisateur.setTypeVoie("rue");
        newUtilisateur.setVille("Testville");
        newUtilisateur.setCodePostal("57860");
        newUtilisateur.setCursus("CDA Java");
        newUtilisateur.setTelephone("+336744552");
        newUtilisateur.setMotDePasse("rooT17moodloc!");*/

        User newUtilisatrice = new User();
        newUser.setNom("MmeTest");
        newUser.setPrenom("Roberte");
        /*newUtilisateur.setEmail("roberte.mmetest@gmail.com");
        newUtilisateur.setAdresse("du test");
        newUtilisateur.setNumeroAdresse("100");
        newUtilisateur.setTypeVoie("rue");
        newUtilisateur.setVille("Testville");
        newUtilisateur.setCodePostal("57860");
        newUtilisateur.setCursus("CDA Java");
        newUtilisateur.setTelephone("+336744553");
        newUtilisateur.setMotDePasse("rooT17floodloc!");*/

        utilisateursListTest.add(newUser);
        utilisateursListTest.add(newUtilisatrice);

        // When:
        when(userRepositoryTest.findAll()).thenReturn(utilisateursListTest);

        // Then:
        assertEquals(utilisateursListTest, utilisateurServiceImpTest.getAllUsers());
    }

    @Test
    void testRetourGetUserById_doitRetournerUtilisateur() {
        // Given:
        User newUser = new User();
        newUser.setId(100);
        newUser.setNom("MrTest");

        Optional<User> lookForUser = Optional.of(newUser);

        // When
        when(userRepositoryTest.findById(100)).thenReturn(lookForUser);

        // Then
        assertEquals(newUser, utilisateurServiceImpTest.getUserById(100));
    }

    @Test
    void testRetourDeleteUserById_doitRetournerConfirmation() {
        // Given:
        User newUser = new User();
        newUser.setId(100);
        newUser.setNom("MrTest");

        Optional<User> lookForUser = Optional.of(newUser);

        // When
        when(userRepositoryTest.findById(100)).thenReturn(lookForUser);

        // Then
        assertEquals(HttpStatus.OK, utilisateurServiceImpTest.deleteUserById(100).getStatusCode());
    }

    @Test
    void testRetourUserModification_doitRetournerUtilisateur() {
        // Given:
        User newUser = new User();
        newUser.setId(100);
        newUser.setNom("MrTest");

        User modifiedUser = new User();
        modifiedUser.setId(100);
        modifiedUser.setNom("MrTestReussi");

        Optional <User> monRetourAttendu = Optional.of(modifiedUser);

        // When
        when(userRepositoryTest.findById(100)).thenReturn(monRetourAttendu);

        // Then
        assertEquals(monRetourAttendu.get(), utilisateurServiceImpTest.userModification(100, modifiedUser).getBody());
    }

    @Test
    void testRetourSubscription_doitRetournerUtilisateurInscrit() {
        // Given:
        User suscribedUser = new User();
        suscribedUser.setNom("MrLeNouveau");
        suscribedUser.setEmail("robert.mrtest@gmail.com");
        suscribedUser.setAdresse("du test");
        suscribedUser.setNumeroAdresse("100");
        suscribedUser.setTypeVoie("rue");
        suscribedUser.setVille("Testville");
        suscribedUser.setCodePostal("57860");
        suscribedUser.setCursus("CDA Java");
        suscribedUser.setTelephone("+336744552");
        suscribedUser.setMotDePasse("rooT17moodloc!");

        Role role = new Role();
        role.setId(1);
        role.setNom("Etudiant");

        when(roleRepositoryTest.findById(1)).thenReturn(Optional.of(role));
        when(userRepositoryTest.existsByEmail(suscribedUser.getEmail())).thenReturn(false);
        when(userRepositoryTest.save(suscribedUser)).thenReturn(suscribedUser);

        // When:
        ResponseEntity<Map<String, Object>> response = utilisateurServiceImpTest.subscription(suscribedUser);

        // Then:
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Inscription réussie", response.getBody().get("message"));
        verify(userRepositoryTest, times(1)).save(suscribedUser);
        verify(userRepositoryTest, times(1)).existsByEmail(suscribedUser.getEmail());
        verify(roleRepositoryTest, times(1)).findById(1);
    }

    @Test
    void connection() {
    }
}