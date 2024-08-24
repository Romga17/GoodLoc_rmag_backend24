package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.exception.NotFoundException;
import edu.gdlc_project.gdlc_pckgs.model.User;
import edu.gdlc_project.gdlc_pckgs.security.IsAdmin;
import edu.gdlc_project.gdlc_pckgs.service.User_Service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable int id) {
        try {
            User user = userServiceImp.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }

    }

    @GetMapping("/list")
    public List<User> getUsersList(){
        return userServiceImp.getAllUsers();
    }


    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return userServiceImp.saveUser(user);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<User> modifyUser(@PathVariable int id, @RequestBody User user){
        return userServiceImp.userModification(id, user);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable int id){
        return userServiceImp.deleteUserById(id);
    }

}



