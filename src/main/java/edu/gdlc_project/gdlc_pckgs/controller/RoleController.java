package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Role;
import edu.gdlc_project.gdlc_pckgs.service.Role_Service.RoleService;
import edu.gdlc_project.gdlc_pckgs.service.Role_Service.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/role")
public class RoleController {


    @Autowired
    protected RoleServiceImp roleServiceImp;

    @PostMapping("/add")
    public String addRole(@RequestBody Role role){
        roleServiceImp.saveRole(role);
        return "Le nouveau rôle à été ajouté.";
    }

    @GetMapping("/list")
    public List<Role> getAllRoles(){
        return roleServiceImp.getAllRoles();
    }
}
