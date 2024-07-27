package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Location;
import edu.gdlc_project.gdlc_pckgs.service.Location_Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etablissement")
public class LocationController {

    @Autowired
    protected LocationService locationService;
    @PostMapping("/etablissement")
    public String add(@RequestBody Location location){
        locationService.saveEtablissement(location);
        return "Le nouvel établissement à été ajouté.";
    }

    @GetMapping("/etablissement/liste")
    public List<Location> getAllEtablissement(){
        return getAllEtablissement();
    }
}
