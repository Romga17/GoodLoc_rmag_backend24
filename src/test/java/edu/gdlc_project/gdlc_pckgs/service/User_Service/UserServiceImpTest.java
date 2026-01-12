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
    private UserServiceImp userServiceImpTest;

    @MockBean
    private UserRepository userRepositoryTest;

    @MockBean
    private RoleRepository roleRepositoryTest;

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
    public void testSavedUserReturn_shouldReturnNewUser() throws Exception {
        // Given
        User newUser = new User();
        newUser.setUserLastname("MrTest");
        newUser.setUserFirstname("Robert");
        newUser.setEmail("robert.mrtest@gmail.com");
        newUser.setUserAddress("du test");
        newUser.setUserAddressNumber("100");
        newUser.setUserRoadType("rue");
        newUser.setUserCity("Testville");
        newUser.setUserZipCode("57860");
        newUser.setUserCourse("CDA Java");
        newUser.setUserPhone("+336744552");
        newUser.setUserPassword("rooT17goodloc!");

        // When
        when(userRepositoryTest.save(newUser)).thenReturn(newUser);
        ResponseEntity<User> savedUser = userServiceImpTest.saveUser(newUser);

        // Then
        assertEquals(newUser, savedUser.getBody());
        verify(userRepositoryTest, times(1)).save(newUser);
    }

    @Test
    public void testGetAllUsersReturn_shouldReturnUsersList() throws Exception {
        // Given:
        List<User> usersListTest = new ArrayList<>();

        User newUser = new User();
        newUser.setUserLastname("MrTest");
        newUser.setUserFirstname("Robert");

        User newUtilisatrice = new User();
        newUser.setUserLastname("MmeTest");
        newUser.setUserFirstname("Roberte");

        usersListTest.add(newUser);
        usersListTest.add(newUtilisatrice);

        // When:
        when(userRepositoryTest.findAll()).thenReturn(usersListTest);

        // Then:
        assertEquals(usersListTest, userServiceImpTest.getAllUsers());
    }

    @Test
    void testGetUserByIdReturn_shouldReturnTargetedUser() {
        // Given:
        User newUser = new User();
        newUser.setId(100);
        newUser.setUserLastname("MrTest");

        Optional<User> lookForUser = Optional.of(newUser);

        // When
        when(userRepositoryTest.findById(100)).thenReturn(lookForUser);

        // Then
        assertEquals(newUser, userServiceImpTest.getUserById(100));
    }

    @Test
    void testRetourDeleteUserById_doitRetournerConfirmation() {
        // Given:
        User newUser = new User();
        newUser.setId(100);
        newUser.setUserLastname("MrTest");

        Optional<User> lookForUser = Optional.of(newUser);

        // When
        when(userRepositoryTest.findById(100)).thenReturn(lookForUser);

        // Then
        assertEquals(HttpStatus.OK, userServiceImpTest.deleteUserById(100).getStatusCode());
    }

    @Test
    void testUserModificationReturn_shouldReturnModifiedUser() {
        // Given:
        User newUser = new User();
        newUser.setId(100);
        newUser.setUserLastname("MrTest");

        User modifiedUser = new User();
        modifiedUser.setId(100);
        modifiedUser.setUserLastname("MrTestReussi");

        Optional <User> monRetourAttendu = Optional.of(modifiedUser);

        // When
        when(userRepositoryTest.findById(100)).thenReturn(monRetourAttendu);

        // Then
        assertEquals(monRetourAttendu.get(), userServiceImpTest.userModification(100, modifiedUser).getBody());
    }

    @Test
    void testRetourSubscription_doitRetournerUtilisateurInscrit() {
        // Given:
        User suscribedUser = new User();
        suscribedUser.setUserLastname("MrLeNouveau");
        suscribedUser.setEmail("robert.mrtest@gmail.com");
        suscribedUser.setUserAddress("du test");
        suscribedUser.setUserAddressNumber("100");
        suscribedUser.setUserRoadType("rue");
        suscribedUser.setUserCity("Testville");
        suscribedUser.setUserZipCode("57860");
        suscribedUser.setUserCourse("CDA Java");
        suscribedUser.setUserPhone("+336744552");
        suscribedUser.setUserPassword("rooT17moodloc!");

        Role role = new Role();
        role.setId(1);
        role.setRoleName("Etudiant");

        when(roleRepositoryTest.findById(1)).thenReturn(Optional.of(role));
        when(userRepositoryTest.existsByEmail(suscribedUser.getEmail())).thenReturn(false);
        when(userRepositoryTest.save(suscribedUser)).thenReturn(suscribedUser);

        // When:
        ResponseEntity<Map<String, Object>> response = userServiceImpTest.subscription(suscribedUser);

        // Then:
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Inscription réussie", response.getBody().get("message"));
        verify(userRepositoryTest, times(1)).save(suscribedUser);
        verify(userRepositoryTest, times(1)).existsByEmail(suscribedUser.getEmail());
        verify(roleRepositoryTest, times(1)).findById(1);
    }

    @Test
    void connection() {
    }
}