package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Status;
import edu.gdlc_project.gdlc_pckgs.service.Status_Service.StatusService;
import edu.gdlc_project.gdlc_pckgs.service.Status_Service.StatusServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/status")
public class StatusController {

    @Autowired
    protected StatusServiceImp statusServiceImp;

    @GetMapping("/list")
    public List<Status> getAllStatus(){
        return statusServiceImp.getAllStatuses();
    }

    @PostMapping("/add")
    public String addStatus(@RequestBody Status status){
        statusServiceImp.saveStatus(status);
        return "Le nouvel état à été ajouté.";
    }

    // Control status
}
