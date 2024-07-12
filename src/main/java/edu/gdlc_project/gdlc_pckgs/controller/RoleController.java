package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Role;
import edu.gdlc_project.gdlc_pckgs.service.Service_Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/role")
public class RoleController {


    @Autowired
    protected RoleService roleService;

    @PostMapping("/role")
    public String add(@RequestBody Role role){
        roleService.saveRole(role);
        return "Le nouveau rôle à été ajouté.";
    }

    @GetMapping("/role/liste")
    public List<Role> getAllRole(){
        return roleService.getAllRole();
    }
}
