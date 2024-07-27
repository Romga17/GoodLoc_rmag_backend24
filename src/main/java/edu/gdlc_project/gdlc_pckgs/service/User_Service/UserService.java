package edu.gdlc_project.gdlc_pckgs.service.User_Service;

import edu.gdlc_project.gdlc_pckgs.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {
    public ResponseEntity<User> saveUser(User user);

    public List<User> getAllUsers();

    public User getUserById(int id);

    public ResponseEntity<String> deleteUserById(int id);

    public ResponseEntity<User> userModification(int id, User user);

    public ResponseEntity<Map<String, Object>> subscription(User user);

    public ResponseEntity<Map<String, Object>> connection(User user);
}