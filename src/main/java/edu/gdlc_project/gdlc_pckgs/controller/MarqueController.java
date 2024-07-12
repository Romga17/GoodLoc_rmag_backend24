package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Marque;
import edu.gdlc_project.gdlc_pckgs.service.Service_Marque.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marque")
public class MarqueController {

    @Autowired
    protected MarqueService marqueService;

    @PostMapping("/marque")
    public String add(@RequestBody Marque marque){
        marqueService.saveMarque(marque);
        return "La nouvelle marque à été ajoutée.";
    }

    @GetMapping("/marque/liste")
    public List<Marque> getAllMarque(){
        return getAllMarque();
    }



}
