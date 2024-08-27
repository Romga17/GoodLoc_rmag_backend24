package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.exception.NotFoundException;
import edu.gdlc_project.gdlc_pckgs.model.Role;
import edu.gdlc_project.gdlc_pckgs.model.User;
import edu.gdlc_project.gdlc_pckgs.repository.RoleRepository;
import edu.gdlc_project.gdlc_pckgs.service.Role_Service.RoleService;
import edu.gdlc_project.gdlc_pckgs.service.Role_Service.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleServiceImp roleServiceImp;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/list")
    public List<Role> getAllRoles() {
        return roleServiceImp.getAllRoles();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Role> getSingleRole(@PathVariable int id) {
        System.out.println(id);
        try {
            Role role = roleServiceImp.getRoleById(id);
            return ResponseEntity.ok(role);
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/add")
    public String addRole(@RequestBody Role role) {
        roleServiceImp.saveRole(role);
        return "Le nouveau rôle à été ajouté.";
    }

}


