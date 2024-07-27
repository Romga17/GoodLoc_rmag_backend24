package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Status;
import edu.gdlc_project.gdlc_pckgs.service.Status_Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/etat")
public class StatusController {

    @Autowired
    protected StatusService statusService;

    @PostMapping("/etat")
    public String add(@RequestBody Status status){
        statusService.saveEtat(status);
        return "Le nouvel état à été ajouté.";
    }

    @GetMapping("/etat/liste")
    public List<Status> getAllEtat(){
        return getAllEtat();
    }
}
