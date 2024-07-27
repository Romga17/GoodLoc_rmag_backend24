package edu.gdlc_project.gdlc_pckgs.service.Location_Service;

import edu.gdlc_project.gdlc_pckgs.model.Location;
import edu.gdlc_project.gdlc_pckgs.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImp implements LocationService {

    private LocationRepository locationRepository;

    @Override
    public Location saveEtablissement(Location location) {
        return null;
    }

    @Override
    public List<Location> getAllEtablissement() {
        return null;
    }
}
