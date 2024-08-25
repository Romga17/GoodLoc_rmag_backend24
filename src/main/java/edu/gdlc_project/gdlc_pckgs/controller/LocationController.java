package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Location;
import edu.gdlc_project.gdlc_pckgs.service.Location_Service.LocationService;
import edu.gdlc_project.gdlc_pckgs.service.Location_Service.LocationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/location")
public class LocationController {

    @Autowired
    protected LocationServiceImp locationServiceImp;

    @PostMapping("/add")
    public String add(@RequestBody Location location){
        locationServiceImp.saveLocation(location);
        return "Le nouvel établissement à été ajouté.";
    }

    @GetMapping("/list")
    public List<Location> getLocationsList(){
        return locationServiceImp.getAllLocations();
    }
}
