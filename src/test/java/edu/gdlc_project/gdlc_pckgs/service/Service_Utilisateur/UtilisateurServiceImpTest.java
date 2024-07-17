package edu.gdlc_project.gdlc_pckgs.service.Service_Utilisateur;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdlc_project.gdlc_pckgs.Goodloc24Application;
import edu.gdlc_project.gdlc_pckgs.model.Role;
import edu.gdlc_project.gdlc_pckgs.model.Utilisateur;
import edu.gdlc_project.gdlc_pckgs.repository.MaterielRepository;
import edu.gdlc_project.gdlc_pckgs.repository.RoleRepository;
import edu.gdlc_project.gdlc_pckgs.repository.UtilisateurRepository;
import edu.gdlc_project.gdlc_pckgs.service.EmailServiceImp;
import edu.gdlc_project.gdlc_pckgs.service.Service_Materiel.MaterielServiceImp;
import net.bytebuddy.dynamic.DynamicType;
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
class UtilisateurServiceImpTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UtilisateurServiceImp utilisateurServiceImpTest;

    @MockBean
    private UtilisateurRepository utilisateurRepositoryTest;

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
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setNom("MrTest");
        newUtilisateur.setPrenom("Robert");
        newUtilisateur.setEmail("robert.mrtest@gmail.com");
        newUtilisateur.setAdresse("du test");
        newUtilisateur.setNumeroAdresse("100");
        newUtilisateur.setTypeVoie("rue");
        newUtilisateur.setVille("Testville");
        newUtilisateur.setCodePostal("57860");
        newUtilisateur.setCursus("CDA Java");
        newUtilisateur.setTelephone("+336744552");
        newUtilisateur.setMotDePasse("rooT17goodloc!");

        // When
        when(utilisateurRepositoryTest.save(newUtilisateur)).thenReturn(newUtilisateur);
        ResponseEntity<Utilisateur> savedUtilisateur = utilisateurServiceImpTest.saveUtilisateur(newUtilisateur);

        // Then
        assertEquals(newUtilisateur, savedUtilisateur.getBody());
        verify(utilisateurRepositoryTest, times(1)).save(newUtilisateur);
    }

    @Test
    public void testRetourgetAllUsers_doitRetournerListeUtilisateurs() throws Exception {
        // Given:
        List<Utilisateur> utilisateursListTest = new ArrayList<>();

        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setNom("MrTest");
        newUtilisateur.setPrenom("Robert");
        /*newUtilisateur.setEmail("robert.mrtest@gmail.com");
        newUtilisateur.setAdresse("du test");
        newUtilisateur.setNumeroAdresse("100");
        newUtilisateur.setTypeVoie("rue");
        newUtilisateur.setVille("Testville");
        newUtilisateur.setCodePostal("57860");
        newUtilisateur.setCursus("CDA Java");
        newUtilisateur.setTelephone("+336744552");
        newUtilisateur.setMotDePasse("rooT17moodloc!");*/

        Utilisateur newUtilisatrice = new Utilisateur();
        newUtilisateur.setNom("MmeTest");
        newUtilisateur.setPrenom("Roberte");
        /*newUtilisateur.setEmail("roberte.mmetest@gmail.com");
        newUtilisateur.setAdresse("du test");
        newUtilisateur.setNumeroAdresse("100");
        newUtilisateur.setTypeVoie("rue");
        newUtilisateur.setVille("Testville");
        newUtilisateur.setCodePostal("57860");
        newUtilisateur.setCursus("CDA Java");
        newUtilisateur.setTelephone("+336744553");
        newUtilisateur.setMotDePasse("rooT17floodloc!");*/

        utilisateursListTest.add(newUtilisateur);
        utilisateursListTest.add(newUtilisatrice);

        // When:
        when(utilisateurRepositoryTest.findAll()).thenReturn(utilisateursListTest);

        // Then:
        assertEquals(utilisateursListTest, utilisateurServiceImpTest.getAllUsers());
    }

    @Test
    void testRetourGetUserById_doitRetournerUtilisateur() {
        // Given:
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setId(100);
        newUtilisateur.setNom("MrTest");

        Optional<Utilisateur> lookForUser = Optional.of(newUtilisateur);

        // When
        when(utilisateurRepositoryTest.findById(100)).thenReturn(lookForUser);

        // Then
        assertEquals(newUtilisateur, utilisateurServiceImpTest.getUserById(100));
    }

    @Test
    void testRetourDeleteUserById_doitRetournerConfirmation() {
        // Given:
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setId(100);
        newUtilisateur.setNom("MrTest");

        Optional<Utilisateur> lookForUser = Optional.of(newUtilisateur);

        // When
        when(utilisateurRepositoryTest.findById(100)).thenReturn(lookForUser);

        // Then
        assertEquals(HttpStatus.OK, utilisateurServiceImpTest.deleteUserById(100).getStatusCode());
    }

    @Test
    void testRetourUserModification_doitRetournerUtilisateur() {
        // Given:
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setId(100);
        newUtilisateur.setNom("MrTest");

        Utilisateur modifiedUtilisateur = new Utilisateur();
        modifiedUtilisateur.setId(100);
        modifiedUtilisateur.setNom("MrTestReussi");

        Optional <Utilisateur> monRetourAttendu = Optional.of(modifiedUtilisateur);

        // When
        when(utilisateurRepositoryTest.findById(100)).thenReturn(monRetourAttendu);

        // Then
        assertEquals(monRetourAttendu.get(), utilisateurServiceImpTest.userModification(100,modifiedUtilisateur).getBody());
    }

    @Test
    void testRetourSubscription_doitRetournerUtilisateurInscrit() {
        // Given:
        Utilisateur suscribedUtilisateur = new Utilisateur();
        suscribedUtilisateur.setNom("MrLeNouveau");
        suscribedUtilisateur.setEmail("robert.mrtest@gmail.com");
        suscribedUtilisateur.setAdresse("du test");
        suscribedUtilisateur.setNumeroAdresse("100");
        suscribedUtilisateur.setTypeVoie("rue");
        suscribedUtilisateur.setVille("Testville");
        suscribedUtilisateur.setCodePostal("57860");
        suscribedUtilisateur.setCursus("CDA Java");
        suscribedUtilisateur.setTelephone("+336744552");
        suscribedUtilisateur.setMotDePasse("rooT17moodloc!");

        Role role = new Role();
        role.setId(1);
        role.setNom("Etudiant");

        when(roleRepositoryTest.findById(1)).thenReturn(Optional.of(role));
        when(utilisateurRepositoryTest.existsByEmail(suscribedUtilisateur.getEmail())).thenReturn(false);
        when(utilisateurRepositoryTest.save(suscribedUtilisateur)).thenReturn(suscribedUtilisateur);

        // When:
        ResponseEntity<Map<String, Object>> response = utilisateurServiceImpTest.subscription(suscribedUtilisateur);

        // Then:
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Inscription réussie", response.getBody().get("message"));
        verify(utilisateurRepositoryTest, times(1)).save(suscribedUtilisateur);
        verify(utilisateurRepositoryTest, times(1)).existsByEmail(suscribedUtilisateur.getEmail());
        verify(roleRepositoryTest, times(1)).findById(1);
    }

    @Test
    void connection() {
    }
}

//Ajout d'un commentaire inutile pour push.